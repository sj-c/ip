// Storage.java

package seedu.duke.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;



import seedu.duke.task.*;
import seedu.duke.exception.DukeException;


public final class Storage {
    private final Path file;

    public Storage(String filePath) {
        this.file = Paths.get(filePath);
    }

    /** Create folder/file if missing, then load tasks. */
    public List<Task> load() throws DukeException {
        try {
            Path parent = file.getParent();
            if (parent != null && Files.notExists(parent)) {
                Files.createDirectories(parent);
            }
            if (Files.notExists(file)) {
                Files.createFile(file);
                return new ArrayList<>();
            }

            List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
            List<Task> tasks = new ArrayList<>();
            for (String raw : lines) {
                String line = raw.trim();
                if (line.isEmpty()) continue;
                try {
                    tasks.add(parseLine(line));
                } catch (IllegalArgumentException ex) {
                    // Stretch goal handling: skip corrupted lines
                    // You could also collect and report them via Ui.
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Failed to load tasks from " + file, e);
        }
    }

    /** Save whole list snapshot to disk (simple & robust). */
    public void save(TaskList tasks) throws DukeException {
        // (Optional) atomic save via temp file:
        Path tmp = file.resolveSibling(file.getFileName().toString() + ".tmp");
        try (BufferedWriter w = Files.newBufferedWriter(
                tmp, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

            for (int i = 0; i < tasks.size(); i++) {
                w.write(tasks.get(i).toSaveString());
                w.newLine();
            }
        } catch (IOException e) {
            throw new DukeException("Failed to write temp file for " + file, e);
        }

        try {
            Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new DukeException("Failed to replace " + file + " with temp file", e);
        }
    }

    // Format: TYPE|done|name|extras...
    private Task parseLine(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length < 3) throw new IllegalArgumentException("Bad format: " + line);

        String type = parts[0].trim();
        boolean done = parts[1].trim().equals("1");
        String name = parts[2].trim();

        Task t;
        switch (type) {
            case "T":
                t = new ToDo(name);
                break;
            case "D":
                if (parts.length < 4) throw new IllegalArgumentException("Deadline missing /by");
                // Deadline will parse ISO or flexible formats itself
                t = new Deadline(name, parts[3].trim());
                break;
            case "E":
                if (parts.length < 5) throw new IllegalArgumentException("Event missing /from or /to");
                t = new Event(name, parts[3].trim(), parts[4].trim());
                break;
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }

        t.setDone(done);
        return t;
    }
}

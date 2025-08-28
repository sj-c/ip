import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

final class Storage {
    private static final Path FILE = Paths.get("data", "duke.txt");

    static void initAndLoad() {
        try {
            if (Files.notExists(FILE.getParent())) {
                Files.createDirectories(FILE.getParent());
            }
            if (Files.notExists(FILE)) {
                Files.createFile(FILE);
            }
            List<String> lines = Files.readAllLines(FILE, StandardCharsets.UTF_8);
            for (String line : lines) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) continue;
                try {
                    parseAndAdd(trimmed);
                } catch (IllegalArgumentException ex) {
                    System.out.println("Skipping corrupted line: " + trimmed);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to initialize storage: " + e.getMessage());
        }
    }

    static void saveAll() {
        try (BufferedWriter w = Files.newBufferedWriter(
                FILE, StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Task t : Task.all()) {
                w.write(t.toSaveString());
                w.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }

    private static void parseAndAdd(String line) {
        // Format: TYPE|done|name|extras...
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
                t = new Deadline(name, parts[3].trim());
                break;
            case "E":
                if (parts.length < 5) throw new IllegalArgumentException("Event missing /from or /to");
                t = new Event(name, parts[3].trim(), parts[4].trim());
                break;
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }

        t.done = done;
    }
}

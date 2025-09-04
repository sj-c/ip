package seedu.duke.command;

import java.util.List;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Finds tasks whose descriptions contain a given keyword (case-insensitive).
 */
public final class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a {@code FindCommand}.
     *
     * @param keyword non-empty search keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (keyword.isEmpty()) {
            throw new DukeException("Keyword cannot be empty. Usage: find <keyword>");
        }

        List<Task> matches = tasks.findByKeyword(keyword);

        StringBuilder sb = new StringBuilder();
        if (matches.isEmpty()) {
            sb.append("     No matching tasks found.\n");
        } else {
            sb.append("     Here are the matching tasks in your list:\n");
            for (int i = 0; i < matches.size(); i++) {
                sb.append("     ")
                        .append(i + 1)
                        .append(".")
                        .append(matches.get(i))
                        .append("\n");
            }
        }

        ui.show(sb.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

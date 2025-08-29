package seedu.duke.command;

import java.util.List;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Finds tasks whose descriptions contain a given keyword (case-insensitive) and shows them.
 */
public final class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a FindCommand.
     *
     * @param keyword Search keyword; must be non-null and non-empty after trimming.
     */
    public FindCommand(String keyword) {

        this.keyword = keyword.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (keyword.isEmpty()) {
            throw new DukeException("Keyword cannot be empty. Usage: find <keyword>");
        }

        final List<Task> matches = tasks.findByKeyword(keyword);

        StringBuilder sb = new StringBuilder();
        sb.append("    ____________________________________________________________\n");
        if (matches.isEmpty()) {
            sb.append("     No matching tasks found.\n");
        } else {
            sb.append("     Here are the matching tasks in your list:\n");
            for (int i = 0; i < matches.size(); i++) {
                sb.append("     ")
                        .append(i + 1).append(".")
                        .append(matches.get(i))
                        .append("\n");
            }
        }
        sb.append("    ____________________________________________________________\n");

        ui.show(sb.toString());
        // No persistence change; nothing to save.
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

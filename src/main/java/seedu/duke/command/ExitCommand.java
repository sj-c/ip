package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Exits the application.
 */
public class ExitCommand extends Command {

    /**
     * Shows the goodbye message.
     *
     * @param tasks   the task list (unused)
     * @param ui      UI for output
     * @param storage persistent storage (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

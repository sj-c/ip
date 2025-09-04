package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Base type for all executable commands.
 */
public abstract class Command {

    /**
     * Executes this command.
     *
     * @param tasks   the task list
     * @param ui      UI for output
     * @param storage persistent storage
     * @throws DukeException on command-specific failures
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Whether this command should terminate the application.
     *
     * @return true to request exit; false otherwise
     */
    public boolean isExit() {
        return false;
    }
}

package seedu.duke.command;

import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.storage.Storage;
import seedu.duke.exception.DukeException;


public class DeleteCommand extends Command {

    private final int idx;
    public DeleteCommand(int idx) { this.idx = idx; }

    /**
     * Runs the command to delete task.
     *
     * @param tasks  List of Tasks.
     * @param ui Interface for Chatnius questions.
     * @param storage Where the data is stored.
     * @throws DukeException  If date and time is in the wrong format
     */
    @Override public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.show(tasks.deleteMessage(idx));
        storage.save(tasks);
    }
}

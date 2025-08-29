package seedu.duke.command;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.storage.Storage;
import seedu.duke.exception.DukeException;


public class ListCommand extends Command {

    /**
     * Runs the command to list task.
     *
     * @param tasks  List of Tasks.
     * @param ui Interface for Chatnius questions.
     * @param storage Where the data is stored.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(tasks.renderList());
    }


}
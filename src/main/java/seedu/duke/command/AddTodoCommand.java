package seedu.duke.command;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.storage.Storage;
import seedu.duke.exception.DukeException;
import seedu.duke.task.ToDo;


public class AddTodoCommand extends Command {

    private final String name;

    public AddTodoCommand(String name) {
        this.name = name;
    }

    /**
     * Runs the command to create a todo task.
     *
     * @param tasks  List of Tasks.
     * @param ui Interface for Chatnius questions.
     * @param storage Where the data is stored.
     * @throws DukeException  If date and time is in the wrong format
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = new ToDo(name);
        tasks.add(t);
        ui.show(tasks.addedMessage(t));
        storage.save(tasks);
    }


}
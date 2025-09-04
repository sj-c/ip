package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;
import seedu.duke.ui.Ui;

/**
 * Adds a simple {@link ToDo} task.
 */
public class AddTodoCommand extends Command {

    private final String name;

    /**
     * Creates a command to add a todo task.
     *
     * @param name description of the task
     */
    public AddTodoCommand(String name) {
        this.name = name;
    }

    /**
     * Executes the command to add a {@link ToDo}.
     *
     * @param tasks   the task list
     * @param ui      UI for output
     * @param storage persistent storage
     * @throws DukeException never thrown here, but kept for symmetry
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = new ToDo(name);
        tasks.add(t);
        ui.show(tasks.addedMessage(t));
        storage.save(tasks);
    }
}

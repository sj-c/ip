package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.task.ToDo;


public class AddTodoCommand extends Command {

    private final String name;

    public AddTodoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = new ToDo(name);
        tasks.add(t);
        ui.show(tasks.addedMessage(t));
        storage.save(tasks);
    }


}
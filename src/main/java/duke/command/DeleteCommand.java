package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;


public class DeleteCommand extends Command {
    private final int idx;
    public DeleteCommand(int idx) { this.idx = idx; }
    @Override public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.show(tasks.deleteMessage(idx));
        storage.save(tasks);
    }
}

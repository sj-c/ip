package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;


public class MarkCommand extends Command {
    private final int idx; // 1-based
    public MarkCommand(int idx) { this.idx = idx; }
    @Override public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.mark(idx); // throws InvalidTaskIndexException on bad idx
        ui.show("____________________________________________________________\n"
                + " Nice! I've marked this task as done:\n"
                + " " + t + "\n"
                + "____________________________________________________________\n");
        storage.save(tasks);
    }
}


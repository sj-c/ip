package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.task.Deadline;

public class AddDeadlineCommand extends Command {
    private final String name;
    private final String byRaw;

    public AddDeadlineCommand(String name, String byRaw) {
        this.name = name;
        this.byRaw = byRaw;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (name == null || name.isBlank() || byRaw == null || byRaw.isBlank()) {
            throw new DukeException("Invalid deadline format! Use: deadline <task> /by <time>");
        }
        try {
            Task t = new Deadline(name, byRaw);  // parses via DateTimeUtil
            tasks.add(t);
            ui.show(tasks.addedMessage(t));
            storage.save(tasks);
        } catch (IllegalArgumentException e) {
            // thrown by DateTimeUtil when date/time can't be parsed
            throw new DukeException("Unrecognized date/time for deadline: " + byRaw, e);
        }
    }
}

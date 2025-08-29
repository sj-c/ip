package seedu.duke.command;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.storage.Storage;
import seedu.duke.exception.DukeException;
import seedu.duke.task.Deadline;

public class AddDeadlineCommand extends Command {
    private final String name;
    private final String byRaw;

    /**
     * Creates Deadline Task
     *
     * @param name  Name of Task.
     * @param byRaw DueDate in String format.
     */
    public AddDeadlineCommand(String name, String byRaw) {
        this.name = name;
        this.byRaw = byRaw;
    }

    /**
     * Runs the command to create a deadline task.
     *
     * @param tasks  List of Tasks.
     * @param ui Interface for Chatnius questions.
     * @param storage Where the data is stored.
     * @throws DukeException  If date and time is in the wrong format
     */
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

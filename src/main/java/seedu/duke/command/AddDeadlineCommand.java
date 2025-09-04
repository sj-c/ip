package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Adds a {@link Deadline} task parsed from the user input.
 * Expects input in the form {@code description /by <time>}.
 */
public class AddDeadlineCommand extends Command {

    private final String name;
    private final String byRaw;

    /**
     * Creates a command to add a deadline task.
     *
     * @param name  description of the task
     * @param byRaw due date/time in a parseable string format
     */
    public AddDeadlineCommand(String name, String byRaw) {
        this.name = name;
        this.byRaw = byRaw;
    }

    /**
     * Executes the command to create a {@link Deadline}.
     *
     * @param tasks   the task list
     * @param ui      UI for output
     * @param storage persistent storage
     * @throws DukeException if the input is invalid or date/time cannot be parsed
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (name == null || name.isBlank() || byRaw == null || byRaw.isBlank()) {
            throw new DukeException("Invalid deadline format! Use: deadline <task> /by <time>");
        }
        try {
            Task t = new Deadline(name, byRaw);
            tasks.add(t);
            ui.show(tasks.addedMessage(t));
            storage.save(tasks);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Unrecognized date/time for deadline: " + byRaw, e);
        }
    }
}

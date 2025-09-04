package seedu.duke.command;

import java.time.LocalDateTime;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.util.DateTimeUtil;

/**
 * Adds an {@link Event} task parsed from the user input.
 * Expects input in the form {@code description /from <start> /to <end>}.
 */
public class AddEventCommand extends Command {

    private final String name;
    private final String fromRaw;
    private final String toRaw;

    /**
     * Creates a command to add an event.
     *
     * @param name    description of the event
     * @param fromRaw start date/time in a parseable string format
     * @param toRaw   end date/time in a parseable string format
     */
    public AddEventCommand(String name, String fromRaw, String toRaw) {
        this.name = name;
        this.fromRaw = fromRaw;
        this.toRaw = toRaw;
    }

    /**
     * Executes the command to create an {@link Event}.
     *
     * @param tasks   the task list
     * @param ui      UI for output
     * @param storage persistent storage
     * @throws DukeException if input is invalid or times are malformed / out of order
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (name == null || name.isBlank()
                || fromRaw == null || fromRaw.isBlank()
                || toRaw == null || toRaw.isBlank()) {
            throw new DukeException("Invalid event format! Use: event <task> /from <start> /to <end>");
        }
        try {
            LocalDateTime from = DateTimeUtil.parseFlexibleDateOrDateTime(fromRaw);
            LocalDateTime to = DateTimeUtil.parseFlexibleDateOrDateTime(toRaw);
            if (to.isBefore(from)) {
                throw new DukeException("Event end time must be after start time.");
            }

            Task t = new Event(name, fromRaw, toRaw);
            tasks.add(t);
            ui.show(tasks.addedMessage(t));
            storage.save(tasks);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Unrecognized date/time for event: " + e.getMessage(), e);
        }
    }
}

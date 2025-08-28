import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private final String name;
    private final String fromRaw;
    private final String toRaw;

    public AddEventCommand(String name, String fromRaw, String toRaw) {
        this.name = name;
        this.fromRaw = fromRaw;
        this.toRaw = toRaw;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (name == null || name.isBlank() || fromRaw == null || fromRaw.isBlank() || toRaw == null || toRaw.isBlank()) {
            throw new DukeException("Invalid event format! Use: event <task> /from <start> /to <end>");
        }
        try {
            // Optional: validate ordering before constructing the Event
            LocalDateTime from = DateTimeUtil.parseFlexibleDateOrDateTime(fromRaw);
            LocalDateTime to   = DateTimeUtil.parseFlexibleDateOrDateTime(toRaw);
            if (to.isBefore(from)) {
                throw new DukeException("Event end time must be after start time.");
            }

            Task t = new Event(name, fromRaw, toRaw); // Event parses again; that's fine
            tasks.add(t);
            ui.show(tasks.addedMessage(t));
            storage.save(tasks);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Unrecognized date/time for event: " + e.getMessage(), e);
        }
    }
}

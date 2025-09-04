package seedu.duke.task;

import java.time.LocalDateTime;

import seedu.duke.util.DateTimeUtil;

/**
 * An event task with a start and end date/time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an {@code Event}.
     *
     * @param description description of the event
     * @param fromRaw     start time in a parseable string
     * @param toRaw       end time in a parseable string
     * @throws IllegalArgumentException if date/time parsing fails
     */
    public Event(String description, String fromRaw, String toRaw) {
        super(description);
        this.from = DateTimeUtil.parseFlexibleDateOrDateTime(fromRaw);
        this.to = DateTimeUtil.parseFlexibleDateOrDateTime(toRaw);
    }

    /**
     * Returns the save type code for events.
     *
     * @return {@code "E"}
     */
    @Override
    protected String typeCode() {
        return "E";
    }

    @Override
    protected String[] extraFieldsForSave() {
        return new String[] {
                DateTimeUtil.ISO.format(from),
                DateTimeUtil.ISO.format(to)
        };
    }

    /**
     * Returns a user-friendly description of this event.
     *
     * @return formatted string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTimeUtil.formatFriendly(from)
                + " to: " + DateTimeUtil.formatFriendly(to) + ")";
    }
}

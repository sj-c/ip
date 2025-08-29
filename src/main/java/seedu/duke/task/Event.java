package seedu.duke.task;

import java.time.LocalDateTime;
import seedu.duke.util.DateTimeUtil;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String fromRaw, String toRaw) {
        super(description);
        this.from = DateTimeUtil.parseFlexibleDateOrDateTime(fromRaw);
        this.to   = DateTimeUtil.parseFlexibleDateOrDateTime(toRaw);
    }

    /**
     * Creates the type code for deadline.
     *
     * @return the type code  If date and time is in the wrong format
     */
    @Override
    protected String typeCode() { return "E"; }

    @Override
    protected String[] extraFieldsForSave() {
        return new String[] {
                DateTimeUtil.ISO.format(from),
                DateTimeUtil.ISO.format(to)
        };
    }

    /**
     * Prints Event's details.
     *
     * @return the details of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTimeUtil.formatFriendly(from)
                + " to: "   + DateTimeUtil.formatFriendly(to) + ")";
    }
}

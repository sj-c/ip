package seedu.duke.task;

import java.time.LocalDateTime;

import seedu.duke.util.DateTimeUtil;

/**
 * A deadline task that has a single due date/time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a {@code Deadline}.
     *
     * @param description description of the deadline
     * @param byRaw       due date/time in a parseable string
     * @throws IllegalArgumentException if date/time parsing fails
     */
    public Deadline(String description, String byRaw) {
        super(description);
        this.by = DateTimeUtil.parseFlexibleDateOrDateTime(byRaw);
    }

    /**
     * Returns the save type code for deadlines.
     *
     * @return {@code "D"}
     */
    @Override
    protected String typeCode() {
        return "D";
    }

    @Override
    protected String[] extraFieldsForSave() {
        // Save in ISO format (stable, unambiguous).
        return new String[] { DateTimeUtil.ISO.format(by) };
    }

    /**
     * Returns a user-friendly description of this deadline.
     *
     * @return formatted string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeUtil.formatFriendly(by) + ")";
    }
}

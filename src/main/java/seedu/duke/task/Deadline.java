package seedu.duke.task;

import java.time.LocalDateTime;

import seedu.duke.exception.DukeException;
import seedu.duke.util.DateTimeUtil;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String byRaw) {
        super(description);
        this.by = DateTimeUtil.parseFlexibleDateOrDateTime(byRaw);
    }

    /**
     * Creates the type code for deadline.
     *
     * @return the type code
     */
    @Override
    protected String typeCode() { return "D"; }

    // Save in ISO (stable, unambiguous)
    @Override
    protected String[] extraFieldsForSave() {
        return new String[] {
                DateTimeUtil.ISO.format(by)
        };
    }

    /**
     * Prints Deadline's details.
     *
     * @return the details of Deadline
     */
    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + DateTimeUtil.formatFriendly(by) + ")";
    }
}

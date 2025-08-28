import java.time.LocalDateTime;

class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String byRaw) {
        super(description);
        this.by = DateTimeUtil.parseFlexibleDateOrDateTime(byRaw);
    }

    @Override
    protected String typeCode() { return "D"; }

    // Save in ISO (stable, unambiguous)
    @Override
    protected String[] extraFieldsForSave() {
        return new String[] { DateTimeUtil.ISO.format(by) };
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeUtil.formatFriendly(by) + ")";
    }
}

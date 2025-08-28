import java.time.LocalDateTime;

class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String fromRaw, String toRaw) {
        super(description);
        this.from = DateTimeUtil.parseFlexibleDateOrDateTime(fromRaw);
        this.to   = DateTimeUtil.parseFlexibleDateOrDateTime(toRaw);
    }

    @Override
    protected String typeCode() { return "E"; }

    @Override
    protected String[] extraFieldsForSave() {
        return new String[] {
                DateTimeUtil.ISO.format(from),
                DateTimeUtil.ISO.format(to)
        };
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTimeUtil.formatFriendly(from)
                + " to: "   + DateTimeUtil.formatFriendly(to) + ")";
    }
}

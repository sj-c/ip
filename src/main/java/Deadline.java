class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    protected String typeCode() {
        return "D";
    }

    @Override
    protected String[] extraFieldsForSave() {
        return new String[] { by };
    }

    @Override public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
abstract class Task {
    protected boolean done;
    protected String name;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    // --- getters/setters ---
    public boolean isDone() { return done; }
    public void setDone(boolean v) { this.done = v; }
    public String getName() { return name; }

    // --- stable save format ---
    protected abstract String typeCode();             // "T" / "D" / "E"
    protected String[] extraFieldsForSave() { return new String[0]; }

    public String toSaveString() {
        StringBuilder sb = new StringBuilder();
        sb.append(typeCode()).append("|").append(done ? 1 : 0).append("|").append(name);
        for (String f : extraFieldsForSave()) sb.append("|").append(f);
        return sb.toString();
    }

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + name;
    }
}

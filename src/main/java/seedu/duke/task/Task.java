package seedu.duke.task;

import java.util.Set;

import seedu.duke.util.TagUtil;

/**
 * Base class for all tasks.
 */
public abstract class Task {

    protected boolean done;
    protected String name;

    /**
     * Creates a {@code Task}.
     *
     * @param name task description
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Set<String> getTags() {
        return TagUtil.extractTags(name);
    }

    // --- getters/setters ---

    /**
     * Returns whether the task is completed.
     *
     * @return true if done; false otherwise
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Sets the done status.
     *
     * @param v done state to set
     */
    public void setDone(boolean v) {
        this.done = v;
    }

    /**
     * Returns the task name/description.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    // --- stable save format ---

    /**
     * Type discriminator used in save files ("T" / "D" / "E").
     *
     * @return the type code
     */
    protected abstract String typeCode();

    /**
     * Extra fields appended to the save line after the name.
     *
     * @return array of extra fields (may be empty)
     */
    protected String[] extraFieldsForSave() {
        return new String[0];
    }

    /**
     * Returns a stable single-line representation suitable for persistence.
     *
     * @return save line
     */
    public String toSaveString() {
        StringBuilder sb = new StringBuilder();
        sb.append(typeCode())
                .append("|")
                .append(done ? 1 : 0)
                .append("|")
                .append(name);

        for (String f : extraFieldsForSave()) {
            sb.append("|").append(f);
        }
        return sb.toString();
    }

    /**
     * Returns a user-friendly representation for display.
     *
     * @return formatted string
     */
    @Override
    public String toString() {
        String base = TagUtil.stripTags(name).trim();
        String tags = String.join(" ", getTags()); // already lowercased in TagUtil
        String suffix = tags.isEmpty() ? "" : " " + tags;
        // If base becomes empty (name was only tags), fall back to original name.
        String display = base.isEmpty() ? name : base + suffix;
        return (done ? "[X] " : "[ ] ") + display;
    }
}

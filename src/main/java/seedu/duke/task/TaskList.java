package seedu.duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.duke.exception.InvalidTaskIndexException;

/**
 * A mutable list of {@link Task} with helper operations for commands.
 */
public class TaskList {

    private final List<Task> ls = new ArrayList<>();

    /** Creates an empty task list. */
    public TaskList() {
        // no-op
    }

    /**
     * Creates a task list initialised from an existing list.
     *
     * @param initial tasks to add (may be {@code null})
     */
    public TaskList(List<Task> initial) {
        if (initial != null) {
            ls.addAll(initial);
        }
    }

    /**
     * Adds one or more tasks to the end of the list (varargs supported).
     *
     * @param tasks tasks to add
     */
    public void add(Task... tasks) {
        for (Task t : tasks) {
            ls.add(t);
        }
    }

    /**
     * Returns the task at the given 0-based index.
     *
     * @param idx index (0-based)
     * @return task at index
     */
    public Task get(int idx) {
        return ls.get(idx);
    }

    /**
     * Deletes and returns the task at the given 0-based index.
     *
     * @param idx index (0-based)
     * @return removed task
     */
    public Task deleteAt(int idx) {
        return ls.remove(idx);
    }

    /**
     * Returns the number of tasks.
     *
     * @return size of list
     */
    public int size() {
        return ls.size();
    }

    /**
     * Returns whether the list is empty.
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return ls.isEmpty();
    }

    // 1-based index helpers for commands

    /**
     * Marks the task at the given 1-based index as done.
     *
     * @param oneBasedIndex 1-based index
     * @return the marked task
     * @throws InvalidTaskIndexException if index is out of range
     */
    public Task mark(int oneBasedIndex) throws InvalidTaskIndexException {
        int i = oneBasedIndex - 1;
        if (i < 0 || i >= ls.size()) {
            throw new InvalidTaskIndexException(oneBasedIndex);
        }
        Task t = ls.get(i);
        t.setDone(true);
        return t;
    }

    /**
     * Marks the task at the given 1-based index as not done.
     *
     * @param oneBasedIndex 1-based index
     * @return the unmarked task
     * @throws InvalidTaskIndexException if index is out of range
     */
    public Task unmark(int oneBasedIndex) throws InvalidTaskIndexException {
        int i = oneBasedIndex - 1;
        if (i < 0 || i >= ls.size()) {
            throw new InvalidTaskIndexException(oneBasedIndex);
        }
        Task t = ls.get(i);
        t.setDone(false);
        return t;
    }

    /**
     * Renders the entire list as a user-friendly multi-line string.
     *
     * @return formatted task list
     */
    public String renderList() {
        StringBuilder result = new StringBuilder();
        if (ls.isEmpty()) {
            result.append("     No items in the list\n");
        } else {
            for (int i = 0; i < ls.size(); i++) {
                result.append("     ")
                        .append(i + 1)
                        .append(". ")
                        .append(ls.get(i).toString())
                        .append("\n");
            }
            result.append(String.format("     Now you have %d tasks in the list.\n", ls.size()));
        }
        return result.toString();
    }

    /**
     * Returns the message shown after adding a task.
     *
     * @param t the task that was added
     * @return user-facing message
     */
    public String addedMessage(Task t) {
        return "Got it. I've added this task:\n"
                + t.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", ls.size());
    }

    /**
     * Deletes a task at 1-based index and returns the message shown.
     *
     * @param oneBasedIndex 1-based index of the task to delete
     * @return user-facing message describing the deletion
     * @throws InvalidTaskIndexException if index is out of range
     */
    public String deleteMessage(int oneBasedIndex) throws InvalidTaskIndexException {
        int i = oneBasedIndex - 1;
        if (i < 0 || i >= ls.size()) {
            throw new InvalidTaskIndexException(oneBasedIndex);
        }
        Task removed = ls.remove(i);
        return "     Noted. I've removed this task:\n"
                + "       " + removed.toString() + "\n"
                + "     Now you have " + ls.size() + " tasks in the list.\n";
    }

    /**
     * Returns tasks whose names contain the given keyword (case-insensitive).
     *
     * @param keyword keyword to search for
     * @return matching tasks in their existing order
     */
    public List<Task> findByKeyword(String keyword) {
        final String needle = keyword.toLowerCase();
        return ls.stream()
                .filter(t -> t.getName().toLowerCase().contains(needle))
                .collect(Collectors.toList());
    }
}

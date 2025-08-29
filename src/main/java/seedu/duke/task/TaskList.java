package seedu.duke.task;

import java.util.ArrayList;
import java.util.List;
import seedu.duke.exception.InvalidTaskIndexException;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> ls = new ArrayList<>();

    public TaskList() {}
    public TaskList(List<Task> initial) {
        if (initial != null) ls.addAll(initial);
    }

    // basic ops
    public void add(Task t) { ls.add(t); }

    public Task get(int idx) {
        return ls.get(idx);
    }
    public Task deleteAt(int idx) {
        return ls.remove(idx);
    }

    public int size() {
        return ls.size();
    }

    public boolean isEmpty() { return ls.isEmpty(); }

    // 1-based index helpers for commands
    /**
     * Marks the Task as Done
     *
     * @param oneBasedIndex the index of the task
     * @return the marked task.
     */
    public Task mark(int oneBasedIndex) throws InvalidTaskIndexException {
        int i = oneBasedIndex - 1;
        if (i < 0 || i >= ls.size()) throw new InvalidTaskIndexException(oneBasedIndex);
        Task t = ls.get(i);
        t.setDone(true);
        return t;
    }

    /**
     * Unmark the Task as Done
     *
     * @param oneBasedIndex the index of the task
     * @throws InvalidTaskIndexException when the index of the task is invalid
     * @return the marked task.
     */
    public Task unmark(int oneBasedIndex) throws InvalidTaskIndexException {
        int i = oneBasedIndex - 1;
        if (i < 0 || i >= ls.size()) throw new InvalidTaskIndexException(oneBasedIndex);
        Task t = ls.get(i);
        t.setDone(false);
        return t;
    }

    /**
     * Formats the list of task
     *
     * @return the formatted task list.
     */
    public String renderList() {
        StringBuilder result = new StringBuilder();
        result.append("____________________________________________________________\n");
        if (ls.isEmpty()) {
            result.append("     No items in the list\n");
        } else {
            for (int i = 0; i < ls.size(); i++) {
                result.append("     ").append(i + 1).append(". ").append(ls.get(i).toString()).append("\n");
            }
            result.append(String.format("     Now you have %d tasks in the list.\n", ls.size()));
        }
        result.append("____________________________________________________________\n");
        return result.toString();
    }

    /**
     * Creates a message for the added task
     *
     * @return the message for the task.
     */
    public String addedMessage(Task t) {
        return "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + t.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", ls.size())
                + "____________________________________________________________\n";
    }

    /**
     * Creates a message for the deleted task
     *
     * @return the message for the task.
     */
    public String deleteMessage(int oneBasedIndex) throws InvalidTaskIndexException {
        int i = oneBasedIndex - 1;
        if (i < 0 || i >= ls.size()) throw new InvalidTaskIndexException(oneBasedIndex);
        Task removed = ls.remove(i);
        return "____________________________________________________________\n"
                + "     Noted. I've removed this task:\n"
                + "       " + removed.toString() + "\n"
                + "     Now you have " + ls.size() + " tasks in the list.\n"
                + "____________________________________________________________\n";
    }

    /**
     * Returns tasks whose names contain the given keyword (case-insensitive).
     *
     * @param keyword Keyword to search for.
     * @return Matching tasks in their existing order.
     */
    public List<Task> findByKeyword(String keyword) {
        final String needle = keyword.toLowerCase();
        return ls.stream()
                .filter(t -> t.getName().toLowerCase().contains(needle))
                .collect(Collectors.toList());
    }

}

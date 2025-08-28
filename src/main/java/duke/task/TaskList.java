package duke.task;

import java.util.ArrayList;
import java.util.List;
import duke.exception.InvalidTaskIndexException;

public class TaskList {
    private final List<Task> ls = new ArrayList<>();

    public TaskList() {}
    public TaskList(List<Task> initial) {
        if (initial != null) ls.addAll(initial);
    }

    // basic ops
    public void add(Task t) { ls.add(t); }
    public Task get(int idx) { return ls.get(idx); }
    public Task deleteAt(int idx) { return ls.remove(idx); }
    public int size() { return ls.size(); }
    public boolean isEmpty() { return ls.isEmpty(); }

    // 1-based index helpers for commands
    public Task mark(int oneBasedIndex) throws InvalidTaskIndexException {
        int i = oneBasedIndex - 1;
        if (i < 0 || i >= ls.size()) throw new InvalidTaskIndexException(oneBasedIndex);
        Task t = ls.get(i);
        t.setDone(true);
        return t;
    }

    public Task unmark(int oneBasedIndex) throws InvalidTaskIndexException {
        int i = oneBasedIndex - 1;
        if (i < 0 || i >= ls.size()) throw new InvalidTaskIndexException(oneBasedIndex);
        Task t = ls.get(i);
        t.setDone(false);
        return t;
    }

    // UI helpers (strings identical to your current output)
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

    public String addedMessage(Task t) {
        return "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + t.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", ls.size())
                + "____________________________________________________________\n";
    }

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
}

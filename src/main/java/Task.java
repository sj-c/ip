import java.util.ArrayList;
import java.util.List;

abstract class Task {
    protected boolean done;
    protected String name;
    private static ArrayList<Task> ls = new ArrayList<>();

    public Task(String name) {
        this.name = name;
        this.done = false;
        ls.add(this);
    }

    // === NEW: stable save format ===
    protected abstract String typeCode();                 // "T" / "D" / "E"
    protected String[] extraFieldsForSave() { return new String[0]; }

    public String toSaveString() {
        StringBuilder sb = new StringBuilder();
        sb.append(typeCode()).append("|").append(done ? 1 : 0).append("|").append(name);
        for (String f : extraFieldsForSave()) {
            sb.append("|").append(f);
        }
        return sb.toString();
    }

    // === NEW: safe read access for saving ===
    public static List<Task> all() { return new ArrayList<>(ls); }

    @Override
    public String toString() {
        return (this.done ? "[X] " : "[ ] ") + name;
    }

    public String returnTask() {
        return "____________________________________________________________\n" +
                " added: " + name + "\n" +
                "____________________________________________________________\n";
    }

    public String outputInsert() {
        return "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                this.toString() + "\n" +
                String.format("Now you have %d tasks in the list.\n", ls.size()) +
                "____________________________________________________________\n";
    }

    public static String list() {
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

    public static String isDone(int num) {
        Task tsk = ls.get(num);
        tsk.done = true;
        return "____________________________________________________________\n" +
                "     Nice! I've marked this task as done:\n" +
                "     " + tsk.toString() + "\n" +
                "____________________________________________________________\n";
    }

    public static String unDone(int num) {
        Task tsk = ls.get(num);
        tsk.done = false;
        return "____________________________________________________________\n" +
                "     OK, I've marked this task as not done yet:\n" +
                "     " + tsk.toString() + "\n" +
                "____________________________________________________________\n";
    }

    public static int getLsSize() {
        return ls.size();
    }

    public static String delete(int num) {
        Task removedTask = ls.remove(num);
        return "____________________________________________________________\n" +
                "     Noted. I've removed this task:\n" +
                "       " + removedTask.toString() + "\n" +
                "     Now you have " + ls.size() + " tasks in the list.\n" +
                "____________________________________________________________\n";
    }
}
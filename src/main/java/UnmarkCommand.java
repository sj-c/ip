public class UnmarkCommand extends Command {
    private final int idx;
    public UnmarkCommand(int idx) { this.idx = idx; }
    @Override public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.unmark(idx);
        ui.show("____________________________________________________________\n"
                + " OK, I've marked this task as not done yet:\n"
                + " " + t + "\n"
                + "____________________________________________________________\n");
        storage.save(tasks);
    }
}


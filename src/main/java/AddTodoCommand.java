public class AddTodoCommand extends Command {

    private final String name;

    public AddTodoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = new ToDo(name);
        tasks.add(t);
        ui.show(tasks.addedMessage(t));
        storage.save(tasks);
    }


}
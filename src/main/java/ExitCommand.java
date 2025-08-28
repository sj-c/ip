public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye(); // prints your quit banner
    }

    @Override
    public boolean isExit() {
        return true; // tells the main loop to stop
    }
}

package seedu.duke;

import seedu.duke.ui.Ui;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.parser.Parser;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;

/**
 * Main logic class for the Chatnius application.
 * <p>
 * Handles initialization of storage, task list, and UI components.
 * Provides both a CLI loop (via {@link #run()}) and a method for GUI interaction
 * (via {@link #getResponse(String)}).
 */
public class Chatnius {

    /** Manages saving/loading tasks from persistent storage. */
    private final Storage storage;

    /** In-memory list of tasks. */
    private final TaskList tasks;

    /** Handles user interactions and buffered outputs. */
    private final Ui ui;

    /**
     * Creates a new {@code Chatnius} instance.
     *
     * @param filePath the file path to load and save task data
     */
    public Chatnius(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        TaskList loaded;
        try {
            loaded = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            loaded = new TaskList();
        }
        this.tasks = loaded;
    }

    /**
     * Runs the CLI version of Chatnius.
     * Continuously reads and executes user commands until an exit command is given.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * Entry point for the CLI program.
     *
     * @param args optional argument to specify the storage file path
     */
    public static void main(String[] args) {
        String filePath = (args.length > 0) ? args[0] : "data/duke.txt";
        new Chatnius(filePath).run();
    }

    /**
     * Handles one command and returns the response as a string.
     * This method is used by the GUI.
     *
     * @param input the raw user input string
     * @return the output string produced by executing the command
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            return ui.flush();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return ui.flush();
        }
    }
}

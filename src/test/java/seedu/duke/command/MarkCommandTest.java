package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.duke.storage.FakeStorage;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;
import seedu.duke.ui.FakeUi;

public class MarkCommandTest {

    @Test
    public void execute_marksTask_doneAndShowsMessage() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"));

        FakeUi ui = new FakeUi();
        FakeStorage storage = new FakeStorage();

        Command cmd = new MarkCommand(1);
        cmd.execute(tasks, ui, storage);

        assertTrue(tasks.get(0).isDone(), "Task should be marked done");
        assertTrue(ui.getLastShown().contains("marked this task as done"),
                "UI output should mention task was marked");
    }
}

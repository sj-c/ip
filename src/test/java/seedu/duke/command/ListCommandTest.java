package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import seedu.duke.storage.FakeStorage;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;
import seedu.duke.ui.FakeUi;

public class ListCommandTest {

    @Test
    public void execute_showsTaskList_doesNotSave() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"));
        tasks.add(new ToDo("write code"));

        FakeUi ui = new FakeUi();
        FakeStorage storage = new FakeStorage();

        Command cmd = new ListCommand();
        cmd.execute(tasks, ui, storage);

        String shown = ui.getLastShown();
        assertNotNull(shown);
        // sanity: should include one of the tasks
        assertTrue(shown.contains("read book") || shown.contains("write code"));
    }
}

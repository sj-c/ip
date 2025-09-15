package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import seedu.duke.storage.FakeStorage;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;
import seedu.duke.ui.FakeUi;

public class DeleteCommandTest {

    @Test
    public void execute_validIndex_deletesTask_andShowsMessage() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"));
        tasks.add(new ToDo("write code"));
        assertEquals(2, tasks.size());

        FakeUi ui = new FakeUi();
        FakeStorage storage = new FakeStorage();

        Command cmd = new DeleteCommand(1); // delete first task
        cmd.execute(tasks, ui, storage);

        // state change
        assertEquals(1, tasks.size());
        assertEquals("write code", tasks.get(0).getName());

        // UI shows something about deletion
        String shown = ui.getLastShown();
        assertNotNull(shown);
        assertTrue(shown.toLowerCase().contains("delete")
                || shown.toLowerCase().contains("removed")
                || shown.toLowerCase().contains("deleted"));
    }
}

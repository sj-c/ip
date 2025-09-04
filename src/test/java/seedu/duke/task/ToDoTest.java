package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class ToDoTest {

    @Test
    public void toString_notDone_correctFormat() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void typeCode_returnsT() {
        ToDo todo = new ToDo("read book");
        assertEquals("T", todo.typeCode());
    }
}

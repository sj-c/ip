package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void toString_hasTypePrefixE() {
        Event e = new Event("team meeting", "2025-08-29T14:00", "2025-08-29T16:00");
        assertTrue(e.toString().startsWith("[E]"),
                "toString() should start with [E]");
    }

    @Test
    public void toString_includesDescriptionAndDates() {
        Event e = new Event("team meeting", "2025-08-29T14:00", "2025-08-29T16:00");
        String output = e.toString();
        assertTrue(output.contains("team meeting"));
        assertTrue(output.contains("from") && output.contains("to"),
                "toString() should include 'from' and 'to' parts");
    }

    @Test
    public void typeCode_isE() {
        Event e = new Event("team meeting", "2025-08-29T14:00", "2025-08-29T16:00");
        assertEquals("E", e.typeCode());
    }

    @Test
    public void extraFieldsForSave_returnsIsoDates() {
        Event e = new Event("team meeting", "2025-08-29T14:00", "2025-08-29T16:00");
        String[] fields = e.extraFieldsForSave();
        assertEquals("2025-08-29T14:00:00", fields[0]);
        assertEquals("2025-08-29T16:00:00", fields[1]);
    }
}

package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void toString_hasTypePrefixD() {
        Deadline d = new Deadline("submit report", "2025-08-30T23:59");
        assertTrue(d.toString().startsWith("[D]"),
                "toString() should start with [D]");
    }

    @Test
    public void typeCode_isD() {
        Deadline d = new Deadline("submit report", "2025-08-30T23:59");
        assertEquals("D", d.typeCode());
    }

    @Test
    public void extraFieldsForSave_returnsIsoDate() {
        Deadline d = new Deadline("submit report", "2025-08-30T23:59");
        String[] fields = d.extraFieldsForSave();
        assertEquals("2025-08-30T23:59:00", fields[0]);
    }
}

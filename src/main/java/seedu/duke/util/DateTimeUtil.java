package seedu.duke.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public final class DateTimeUtil {
    private static final DateTimeFormatter DISP_DATE = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter DISP_DATETIME = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    // Machine-safe save format (ISO 8601)
    public static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static final DateTimeFormatter[] DATE_PATTERNS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d/M/yyyy")
    };
    private static final DateTimeFormatter[] DATETIME_PATTERNS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
    };

    public static LocalDateTime parseFlexibleDateOrDateTime(String s) {
        String t = s.trim();
        // Try full date-time first
        for (DateTimeFormatter f : DATETIME_PATTERNS) {
            try { return LocalDateTime.parse(t, f); } catch (DateTimeParseException ignored) {}
        }
        // Then date-only -> default time = 00:00
        for (DateTimeFormatter f : DATE_PATTERNS) {
            try {
                return LocalDate.parse(t, f).atStartOfDay();
            } catch (DateTimeParseException ignored) {

            }
        }
        // Finally, accept ISO if we read back from storage
        try {
            return LocalDateTime.parse(t, ISO);
        } catch (DateTimeParseException ignored) {

        }

        throw new IllegalArgumentException("Unrecognized date/time: " + s);
    }

    public static String formatFriendly(LocalDateTime dt) {
        if (dt.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            return DISP_DATE.format(dt);
        }
        return DISP_DATETIME.format(dt);
    }
}

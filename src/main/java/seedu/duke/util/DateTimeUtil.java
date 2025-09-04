package seedu.duke.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility helpers for parsing and formatting dates/times.
 */
public final class DateTimeUtil {

    /** Machine-safe save format (ISO 8601). */
    public static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static final DateTimeFormatter DISP_DATE =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter DISP_DATETIME =
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private static final DateTimeFormatter[] DATE_PATTERNS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d/M/yyyy")
    };

    private static final DateTimeFormatter[] DATETIME_PATTERNS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
    };

    private DateTimeUtil() {
        // utility class; do not instantiate
    }

    /**
     * Parses a date/time string in a few flexible formats.
     * <ul>
     *   <li>Tries date-time patterns first (e.g. {@code yyyy-MM-dd HHmm} or {@code d/M/yyyy HHmm}).</li>
     *   <li>Then tries date-only patterns (time defaulted to 00:00).</li>
     *   <li>Finally accepts ISO-8601 for reading from storage.</li>
     * </ul>
     *
     * @param s input string
     * @return parsed {@link LocalDateTime}
     * @throws IllegalArgumentException if none of the formats match
     */
    public static LocalDateTime parseFlexibleDateOrDateTime(String s) {
        String t = s.trim();

        // Try full date-time first.
        for (DateTimeFormatter f : DATETIME_PATTERNS) {
            try {
                return LocalDateTime.parse(t, f);
            } catch (DateTimeParseException ignored) {
                // try next pattern
            }
        }

        // Then date-only -> default time = 00:00.
        for (DateTimeFormatter f : DATE_PATTERNS) {
            try {
                return LocalDate.parse(t, f).atStartOfDay();
            } catch (DateTimeParseException ignored) {
                // try next pattern
            }
        }

        // Finally, accept ISO if reading back from storage.
        try {
            return LocalDateTime.parse(t, ISO);
        } catch (DateTimeParseException ignored) {
            // fall through to error below
        }

        throw new IllegalArgumentException("Unrecognized date/time: " + s);
    }

    /**
     * Formats a date/time for user display.
     * If the time is exactly midnight, show only the date; otherwise show date and time.
     *
     * @param dt date/time
     * @return formatted string
     */
    public static String formatFriendly(LocalDateTime dt) {
        if (dt.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            return DISP_DATE.format(dt);
        }
        return DISP_DATETIME.format(dt);
    }
}

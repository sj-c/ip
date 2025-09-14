package seedu.duke.util;

import java.util.Set;
import java.util.LinkedHashSet;

/** Tiny utilities for inline #tags inside task names. */
public final class TagUtil {
    private TagUtil() {}

    /** Extracts distinct #tags from a line (e.g., "#fun", "#cs2103t"). */
    public static Set<String> extractTags(String line) {
        Set<String> tags = new LinkedHashSet<>();
        if (line == null) return tags;
        // Split on whitespace; accept tokens starting with '#', at least 2 chars
        for (String tok : line.split("\\s+")) {
            if (tok.length() >= 2 && tok.charAt(0) == '#') {
                // strip trailing punctuation like ',', '.', ';'
                String t = tok.replaceAll("[^#A-Za-z0-9_-]+$", "");
                // normalize to lower-case for matching
                tags.add(t.toLowerCase());
            }
        }
        return tags;
    }

    /** True if the line contains the tag (case-insensitive), e.g., "#fun". */
    public static boolean hasTag(String line, String tagQuery) {
        if (tagQuery == null || tagQuery.isBlank() || tagQuery.charAt(0) != '#') return false;
        String q = tagQuery.toLowerCase();
        return extractTags(line).contains(q);
    }

    public static String stripTags(String line) {
        if (line == null) return "";
        String[] toks = line.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String tok : toks) {
            if (!(tok.startsWith("#") && tok.length() > 1)) {
                if (sb.length() > 0) sb.append(' ');
                sb.append(tok);
            }
        }
        return sb.toString();
    }

}

package pw.lach.linguist.po;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PoParser {
    private static final Pattern NL_PATTERN = Pattern.compile("\\n");

    private static String postProcessLine(String input) {
        return NL_PATTERN.matcher(input).replaceAll("\n");
    }

    public static Map<String, String> poParse(Stream<String> input) {
        var out = new HashMap<String, String>();
        StringBuilder[] thisLine = new StringBuilder[]{null, null};
        Runnable finishLine = () -> {
            if (thisLine[1] == null) {
                throw new IllegalStateException("Line ended, but no msgstr found");
            }
            out.put(
                    postProcessLine(thisLine[0].toString()),
                    postProcessLine(thisLine[1].toString())
            );
            thisLine[0] = null;
            thisLine[1] = null;
        };
        input.forEachOrdered(line -> {
            if (line.startsWith("msgid \"")) {
                if (thisLine[1] != null) {
                    finishLine.run();
                } else if (thisLine[0] != null) {
                    throw new IllegalStateException("Duplicated msgid");
                }
                thisLine[0] = new StringBuilder(line.substring(7, line.length() - 1));
            } else if (line.startsWith("msgstr \"")) {
                if (thisLine[0] == null) {
                    throw new IllegalStateException("Missing msgid");
                } else if (thisLine[1] != null) {
                    throw new IllegalStateException("msgstr is already started");
                }
                thisLine[1] = new StringBuilder(line.substring(8, line.length() - 1));
            } else if (line.startsWith("\"")) {
                var value = line.substring(1, line.length() - 1);
                if (thisLine[1] != null) {
                    thisLine[1].append(value);
                } else if (thisLine[0] != null) {
                    thisLine[0].append(value);
                } else {
                    throw new IllegalStateException("Unexpected string");
                }
            }
        });
        if (thisLine[0] != null) {
            finishLine.run();
        }
        return out;
    }
}

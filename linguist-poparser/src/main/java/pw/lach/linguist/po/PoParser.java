package pw.lach.linguist.po;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PoParser {
    public static Map<String, String> poParse(Stream<String> input) {
        var out = new HashMap<String, String>();
        String[] thisLine = new String[]{null};
        input.forEachOrdered(line -> {
            if (line.startsWith("msgid \"")) {
                if (thisLine[0] != null) {
                    throw new IllegalStateException("Duplicated msgid");
                }
                thisLine[0] = line.substring(7, line.length() - 1);
            } else if (line.startsWith("msgstr \"")) {
                if (thisLine[0] == null) {
                    throw new IllegalStateException("Missing msgid");
                }
                if (out.put(thisLine[0], line.substring(8, line.length() - 1)) != null) {
                    throw new IllegalStateException("Value for msgid \"" + thisLine[0] + "\" is already set!");
                }
                thisLine[0] = null;
            }
        });
        return out;
    }
}

package pw.lach.linguist.spigot.fail;

import java.io.IOException;
import java.util.List;

public class TranslationParseFailure extends IOException {
    private final List<FailedLine> failedLines;

    public TranslationParseFailure(List<FailedLine> failedLines){
        super("Some lines failed to parse");
        this.failedLines = failedLines;
    }

    public List<FailedLine> getFailedLines() {
        return failedLines;
    }
}

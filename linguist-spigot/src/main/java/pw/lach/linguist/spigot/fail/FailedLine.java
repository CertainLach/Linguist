package pw.lach.linguist.spigot.fail;

public class FailedLine {
    private final String line;
    private final Exception failure;

    public FailedLine(String line, Exception failure) {
        this.line = line;
        this.failure = failure;
    }

    public String getLine() {
        return line;
    }

    public Exception getFailure() {
        return failure;
    }
}

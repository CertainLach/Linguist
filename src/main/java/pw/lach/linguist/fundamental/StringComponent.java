package pw.lach.linguist.fundamental;

import pw.lach.linguist.Component;

public abstract class StringComponent<L, O> implements Component<L, O> {
    protected final String string;

    protected StringComponent(String string) {
        this.string = string;
    }
}

package pw.lach.linguist.fundamental;

import java.util.List;

import pw.lach.linguist.IComponent;

public abstract class StringComponent<L, O> implements IComponent<L, O> {
    protected final String string;

    protected StringComponent(String string) {
        this.string = string;
    }
}

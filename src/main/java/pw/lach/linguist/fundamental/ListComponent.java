package pw.lach.linguist.fundamental;

import java.util.List;

import pw.lach.linguist.IComponent;

public abstract class ListComponent<L, O> implements IComponent<L, O> {
    protected final List<IComponent<L, O>> list;

    protected ListComponent(List<IComponent<L, O>> list) {
        this.list = list;
    }
}

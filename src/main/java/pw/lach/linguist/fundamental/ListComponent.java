package pw.lach.linguist.fundamental;

import java.util.List;

import pw.lach.linguist.Component;

public abstract class ListComponent<L, O> implements Component<L, O> {
    protected final List<Component<L, O>> list;

    protected ListComponent(List<Component<L, O>> list) {
        this.list = list;
    }
}

package pw.lach.linguist.fundamental;

import pw.lach.linguist.Component;

public abstract class SlotComponent<L, O> implements Component<L, O> {
    public final int slot;

    protected SlotComponent(int slot) {
        this.slot = slot;
    }
}

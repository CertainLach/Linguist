package pw.lach.linguist.fundamental;

import pw.lach.linguist.IComponent;

public abstract class SlotComponent<L, O> implements IComponent<L, O> {
    protected final int slot;

    protected SlotComponent(int slot) {
        this.slot = slot;
    }
}

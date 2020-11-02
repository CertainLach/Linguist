package pw.lach.linguist.fundamental;

import java.util.List;

import pw.lach.linguist.IComponent;

public abstract class SlotComponent<L, O> implements IComponent<L, O> {
    protected final int slot;

    protected SlotComponent(int slot) {
        this.slot = slot;
    }

    @Override
    public void setProperty(String name, String value) {
        noDataError();
    }

    @Override
    public void setPropertySlot(String name, int slot) {
        noDataError();
    }

    @Override
    public void enableProperty(String name) {
        noDataError();
    }

    @Override
    public void setChildren(List<IComponent<L, O>> components) {
        noDataError();
    }

    private void noDataError() {
        throw new IllegalStateException("Slot components have no data");
    }
}

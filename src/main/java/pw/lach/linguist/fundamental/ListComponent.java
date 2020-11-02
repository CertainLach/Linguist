package pw.lach.linguist.fundamental;

import java.util.List;

import pw.lach.linguist.IComponent;

public abstract class ListComponent<L, O> implements IComponent<L, O> {
    protected final List<IComponent<L, O>> list;

    protected ListComponent(List<IComponent<L, O>> list) {
        this.list = list;
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
        throw new IllegalStateException("List components have no data");
    }
}

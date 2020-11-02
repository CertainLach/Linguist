package pw.lach.linguist.fundamental;

import java.util.List;

import pw.lach.linguist.IComponent;

public abstract class StringComponent<L, O> implements IComponent<L, O> {
    protected final String string;

    protected StringComponent(String string) {
        this.string = string;
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
        throw new IllegalStateException("String components have no data");
    }
}

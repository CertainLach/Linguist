package pw.lach.linguist;

import java.util.List;

public interface IComponent<L, O> {
    void setProperty(String name, String value);

    void setPropertySlot(String name, int slot);

    void enableProperty(String name);

    void setChildren(List<IComponent<L, O>> components);

    default void validate() {
    }

    /**
     * Transform text component into value to render
     */
    public O localize(L locale, Object[] input);
}

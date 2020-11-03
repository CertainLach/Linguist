package pw.lach.linguist;

import java.util.List;

public interface IComponent<L, O> {
    static void notSupported(String what) {
        throw new IllegalStateException(what + " not supported");
    }


    default void setProperty(String name, String value) {
        notSupported(name + " property");
    }

    default void setNamedSlot(String name, int slot) {
        notSupported(name + " named slot");
    }

    default void enableFeature(String name) {
        notSupported(name + " feature");
    }

    default void setChildren(List<IComponent<L, O>> components) {
        notSupported("Children");
    }

    default void validate() {
    }

    /**
     * Transform text component into value to render
     */
    public O localize(L locale, Object[] input);
}

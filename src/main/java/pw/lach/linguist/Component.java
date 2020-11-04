package pw.lach.linguist;

import java.util.List;

public interface Component<L, O> {
    static void notSupported(String what) {
        throw new IllegalStateException(what + " not supported");
    }

    default void setSlot(int slot) {
        notSupported("Value");
    }
    default void setComponent(Component<L, O> component) {
        notSupported("Component");
    }
    default void setProperty(String value) {
        notSupported("Property");
    }

    default void setNamedSlot(String name, int slot) {
        notSupported(name + " slot");
    }
    default void setNamedComponent(String name, Component<L, O> component) {
        notSupported(name + " component");
    }
    default void setNamedProperty(String name, String value) {
        notSupported(name + " property");
    }

    default void enableFeature(String name) {
        notSupported(name + " feature");
    }

    default void setChildren(List<Component<L, O>> components) {
        notSupported("Children");
    }

    default void validate() {
    }

    /**
     * Transform text component into value to render
     */
    public O localize(L locale, Object[] input);
}

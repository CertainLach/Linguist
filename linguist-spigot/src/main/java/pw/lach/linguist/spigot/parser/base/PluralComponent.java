package pw.lach.linguist.spigot.parser.base;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import pw.lach.linguist.Component;
import pw.lach.linguist.spigot.SpigotLocale;

public class PluralComponent implements Component<SpigotLocale, BaseComponent[]> {
    boolean showCount = true;
    int valueSlot = -1;
    Object[] forms = new Object[4];

    @Override
    public void setSlot(int slot) {
        valueSlot = slot;
    }

    @Override
    public void setNamedProperty(String name, String value) {
        switch (name) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                forms[Character.digit(name.charAt(0), 10)] = new TextComponent(value);
                break;
            default:
                Component.notSupported(name + " property");
        }
    }

    @Override
    public void setNamedComponent(String name, Component<SpigotLocale, BaseComponent[]> component) {
        switch (name) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                forms[Character.digit(name.charAt(0), 10)] = component;
                break;
            default:
                Component.notSupported(name + " component");
        }
    }

    @Override
    public void enableFeature(String name) {
        switch (name) {
            case "hideCount":
                showCount = false;
                break;
            default:
                Component.notSupported(name + " feature");
        }
    }

    public void validate() {
        if (valueSlot == -1) {
            throw new IllegalStateException("Value slot is not set");
        }
    }

    @Override
    public BaseComponent[] localize(SpigotLocale locale, Object[] input) {
        int value = (int) input[valueSlot];
        int form = locale.getData().choosePluralForm(value);
        if (forms[form] instanceof Component) {
            //noinspection unchecked
            return ((Component<SpigotLocale, BaseComponent[]>) forms[form]).localize(locale, input);
        }
        if (showCount) {
            return new BaseComponent[]{new TextComponent(String.valueOf(value)), (BaseComponent) forms[form]};
        }
        return new BaseComponent[]{(BaseComponent) forms[form]};
    }
}

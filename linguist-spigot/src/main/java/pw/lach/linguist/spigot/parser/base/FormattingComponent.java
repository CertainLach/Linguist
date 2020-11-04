package pw.lach.linguist.spigot.parser.base;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import pw.lach.linguist.Component;
import pw.lach.linguist.spigot.PreformattedComponent;
import pw.lach.linguist.spigot.SpigotLocale;
import pw.lach.linguist.spigot.parser.SpigotComponent;
import pw.lach.linguist.spigot.parser.fundamental.SpigotListComponent;

import java.util.List;

public class FormattingComponent implements SpigotComponent {
    TextComponent base = new TextComponent();
    List<Component<SpigotLocale, BaseComponent[]>> components;

    // Hovers
    Component<SpigotLocale, BaseComponent[]> text = null;
    int textSlot = -1;
    int itemSlot = -1;
    int entitySlot = -1;

    public void enableColor(ChatColor format) {
        switch (format) {
            case BOLD:
                base.setBold(true);
                break;
            case ITALIC:
                base.setItalic(true);
                break;
            case UNDERLINE:
                base.setUnderlined(true);
                break;
            case STRIKETHROUGH:
                base.setStrikethrough(true);
                break;
            case MAGIC:
                base.setObfuscated(true);
                break;
            default:
                base.setColor(format);
        }
    }

    @Override
    public void enableFeature(String name) {
        var color = ChatColor.valueOf(name.toUpperCase());
        enableColor(color);
    }

    @Override
    public void setNamedSlot(String name, int slot) {
        switch (name) {
            case "item":
                itemSlot = slot;
                break;
            case "entity":
                entitySlot = slot;
                break;
            default:
                Component.notSupported(name + " slot");
        }
    }

    @Override
    public void setNamedComponent(String name, Component<SpigotLocale, BaseComponent[]> component) {
        switch (name) {
            case "text":
                text = component;
                break;
            default:
                Component.notSupported(name + " component");
        }
    }

    @Override
    public void setNamedProperty(String name, String value) {
        ClickEvent.Action action = null;
        switch (name) {
            case "url":
                action = ClickEvent.Action.OPEN_URL;
                break;
            case "file":
                action = ClickEvent.Action.OPEN_FILE;
                break;
            case "cmd":
                action = ClickEvent.Action.RUN_COMMAND;
                break;
            case "suggest":
                action = ClickEvent.Action.SUGGEST_COMMAND;
                break;
            case "page":
                action = ClickEvent.Action.CHANGE_PAGE;
                break;
            default:
                Component.notSupported(name + " property");
                return;
        }
        if (base.getClickEvent() != null) {
            throw new IllegalStateException("Click actions are mutually exclusive!");
        }
        base.setClickEvent(new ClickEvent(action, value));
    }

    @Override
    public void setChildren(List<Component<SpigotLocale, BaseComponent[]>> components) {
        this.components = components;
    }

    @Override
    public void validate() {
        if (components == null) {
            throw new IllegalStateException("No children supplied for formatting component");
        }
        int hovers = itemSlot + entitySlot + textSlot;
        if (text == null) {
            hovers += -1;
        } else if (textSlot != -1) {
            throw new IllegalStateException("Cannot pass text both as slot and component!");
        }
        if (hovers > -3) {
            throw new IllegalStateException("Hover actions are mutually exclusive!");
        }
    }

    @Override
    public BaseComponent[] localize(SpigotLocale locale, Object[] input) {
        var inner = SpigotListComponent.localizeList(components, locale, input);
        var out = new TextComponent(base);
        out.setExtra(inner);
        if (text != null) {
            out.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, text.localize(locale, input)));
        } else if (textSlot != -1) {
            var comp = (PreformattedComponent) input[textSlot];
            out.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, locale.resolveComponent(comp)));
        }
        return new BaseComponent[]{out};
    }
}

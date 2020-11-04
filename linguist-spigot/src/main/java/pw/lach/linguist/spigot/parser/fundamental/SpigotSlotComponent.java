package pw.lach.linguist.spigot.parser.fundamental;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import pw.lach.linguist.fundamental.SlotComponent;
import pw.lach.linguist.spigot.SpigotLocale;
import pw.lach.linguist.spigot.ToComponent;
import pw.lach.linguist.spigot.parser.SpigotComponent;

public class SpigotSlotComponent extends SlotComponent<SpigotLocale, BaseComponent[]> implements SpigotComponent {
    public SpigotSlotComponent(int slot) {
        super(slot);
    }

    @Override
    public BaseComponent[] localize(SpigotLocale locale, Object[] input) {
        var value = input[slot];
        if (value instanceof BaseComponent[]) {
            return (BaseComponent[]) value;
        } else if (value instanceof BaseComponent) {
            return new BaseComponent[]{(BaseComponent) value};
        } else if (value instanceof ToComponent) {
            return locale.resolveComponent(((ToComponent) value).toComponent());
        } else if (value instanceof String) {
            return new BaseComponent[]{new TextComponent((String) value)};
        }
        throw new IllegalStateException("");
    }
}

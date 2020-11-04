package pw.lach.linguist.spigot.parser.fundamental;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import pw.lach.linguist.fundamental.StringComponent;
import pw.lach.linguist.spigot.SpigotLocale;
import pw.lach.linguist.spigot.parser.SpigotComponent;

public class SpigotStringComponent extends StringComponent<SpigotLocale, BaseComponent[]> implements SpigotComponent {
    public SpigotStringComponent(String string) {
        super(string);
    }

    @Override
    public BaseComponent[] localize(SpigotLocale locale, Object[] input) {
        return new BaseComponent[]{new TextComponent(string)};
    }
}

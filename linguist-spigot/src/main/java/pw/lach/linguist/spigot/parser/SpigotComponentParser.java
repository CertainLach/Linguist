package pw.lach.linguist.spigot.parser;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import pw.lach.linguist.Component;
import pw.lach.linguist.ComponentParser;
import pw.lach.linguist.fundamental.ListComponent;
import pw.lach.linguist.fundamental.SlotComponent;
import pw.lach.linguist.fundamental.StringComponent;
import pw.lach.linguist.spigot.SpigotLocale;
import pw.lach.linguist.spigot.parser.base.FormattingComponent;
import pw.lach.linguist.spigot.parser.base.PluralComponent;
import pw.lach.linguist.spigot.parser.fundamental.SpigotListComponent;
import pw.lach.linguist.spigot.parser.fundamental.SpigotSlotComponent;
import pw.lach.linguist.spigot.parser.fundamental.SpigotStringComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SpigotComponentParser extends ComponentParser<SpigotLocale, BaseComponent[]> {
    private static final Map<String, Supplier<Component<SpigotLocale, BaseComponent[]>>> FACTORIES = new HashMap<>();

    public static void registerFactory(String name, Supplier<Component<SpigotLocale, BaseComponent[]>> factory) {
        FACTORIES.put(name, factory);
    }

    public SpigotComponentParser(String input) {
        super(input);
    }

    @Override
    public Component<SpigotLocale, BaseComponent[]> parse() {
        return super.parse();
    }

    @Override
    protected Component<SpigotLocale, BaseComponent[]> componentByName(String name) {
        if (name.charAt(0) == '&') {
            var out = new FormattingComponent();
            for (int i = 1; i < name.length(); i++) {
                var fmt = ChatColor.getByChar(name.charAt(i));
                out.enableColor(fmt);
            }
            return out;
        } else if (name.equals("fmt")) {
            return new FormattingComponent();
        } else if (name.equals("plural")) {
            return new PluralComponent();
        }
        return FACTORIES.get(name).get();
    }

    @Override
    protected StringComponent<SpigotLocale, BaseComponent[]> fundamentalString(String str) {
        return new SpigotStringComponent(str);
    }

    @Override
    protected SlotComponent<SpigotLocale, BaseComponent[]> fundamentalSlot(int slot) {
        return new SpigotSlotComponent(slot);
    }

    @Override
    protected ListComponent<SpigotLocale, BaseComponent[]> fundamentalList(List<Component<SpigotLocale, BaseComponent[]>> components) {
        return new SpigotListComponent(components);
    }
}

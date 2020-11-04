package pw.lach.linguist.spigot.parser.fundamental;

import net.md_5.bungee.api.chat.BaseComponent;
import pw.lach.linguist.Component;
import pw.lach.linguist.fundamental.ListComponent;
import pw.lach.linguist.spigot.SpigotLocale;
import pw.lach.linguist.spigot.parser.SpigotComponent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpigotListComponent extends ListComponent<SpigotLocale, BaseComponent[]> implements SpigotComponent {
    public SpigotListComponent(List<Component<SpigotLocale, BaseComponent[]>> list) {
        super(list);
    }

    public static List<BaseComponent> localizeList(List<Component<SpigotLocale, BaseComponent[]>> list, SpigotLocale locale, Object[] input) {
        return list.stream().map(c -> c.localize(locale, input)).flatMap(Arrays::stream).collect(Collectors.toList());
    }

    @Override
    public BaseComponent[] localize(SpigotLocale locale, Object[] input) {
        if (list.size() == 1) {
            return list.get(0).localize(locale, input);
        }
        return list.stream().map(c -> c.localize(locale, input)).flatMap(Arrays::stream).toArray(BaseComponent[]::new);
    }
}

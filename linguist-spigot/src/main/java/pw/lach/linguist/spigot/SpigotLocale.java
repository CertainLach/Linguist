package pw.lach.linguist.spigot;

import net.md_5.bungee.api.chat.BaseComponent;
import pw.lach.linguist.Component;
import pw.lach.linguist.spigot.data.LocaleData;

public abstract class SpigotLocale {
    public abstract DefaultSpigotLocale getDefault();
    public abstract LocaleData getData();

    public abstract Component<SpigotLocale, BaseComponent[]> parseComponent(String component);

    public BaseComponent[] resolveComponent(PreformattedComponent component) {
        return parseComponent(component.name).localize(this, component.data);
    }
}

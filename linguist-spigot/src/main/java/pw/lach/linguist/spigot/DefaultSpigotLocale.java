package pw.lach.linguist.spigot;

import net.md_5.bungee.api.chat.BaseComponent;
import pw.lach.linguist.Component;
import pw.lach.linguist.spigot.data.LocaleData;
import pw.lach.linguist.spigot.parser.SpigotComponentParser;

import java.util.concurrent.ConcurrentHashMap;

public class DefaultSpigotLocale extends SpigotLocale {
    /**
     * Default locale is lazy resolved by default, because there is no way to get data for original language
     * <p>
     * It is possible to generate this map ahead of time, however.
     */
    private final ConcurrentHashMap<String, Component<SpigotLocale, BaseComponent[]>> parsedComponents = new ConcurrentHashMap<>();
    private final LocaleData data;

    public DefaultSpigotLocale(LocaleData data){
        this.data = data;
    }

    @Override
    public LocaleData getData() {
        return data;
    }

    @Override
    public DefaultSpigotLocale getDefault() {
        return this;
    }

    public Component<SpigotLocale, BaseComponent[]> parseComponent(String component) {
        return parsedComponents.computeIfAbsent(component, c -> new SpigotComponentParser(c).parse());
    }
}

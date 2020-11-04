package pw.lach.linguist.spigot;

import net.md_5.bungee.api.chat.BaseComponent;
import pw.lach.linguist.Component;
import pw.lach.linguist.po.PoParser;
import pw.lach.linguist.spigot.data.LocaleData;
import pw.lach.linguist.spigot.parser.SpigotComponentParser;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ExtraSpigotLocale extends SpigotLocale {
    public final LocaleData data;
    private final DefaultSpigotLocale def;
    public final Map<String, Component<SpigotLocale, BaseComponent[]>> translated = new HashMap<>();

    public ExtraSpigotLocale(LocaleData data, DefaultSpigotLocale def, Stream<String> stream) {
        this.data = data;
        this.def = def;
        try {
            PoParser.poParse(stream).forEach((k, v) -> {
                try {
                    translated.put(k, new SpigotComponentParser(v).parse());
                } catch (Exception e) {
                    System.err.println("Failed to parse \"" + v + "\"");
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            System.err.println("Failed to parse locale data for " + data.getName());
            e.printStackTrace();
        }
    }

    @Override
    public LocaleData getData() {
        return data;
    }

    @Override
    public DefaultSpigotLocale getDefault() {
        return def;
    }

    @Override
    public Component<SpigotLocale, BaseComponent[]> parseComponent(String component) {
        var found = translated.get(component);
        if (found != null) {
            return found;
        }
        return def.parseComponent(component);
    }
}

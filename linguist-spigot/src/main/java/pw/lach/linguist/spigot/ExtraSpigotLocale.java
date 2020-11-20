package pw.lach.linguist.spigot;

import net.md_5.bungee.api.chat.BaseComponent;
import org.junit.internal.runners.statements.Fail;
import pw.lach.linguist.Component;
import pw.lach.linguist.po.PoParser;
import pw.lach.linguist.spigot.data.LocaleData;
import pw.lach.linguist.spigot.fail.FailedLine;
import pw.lach.linguist.spigot.fail.TranslationParseFailure;
import pw.lach.linguist.spigot.parser.SpigotComponentParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ExtraSpigotLocale extends SpigotLocale {
    public final LocaleData data;
    private final DefaultSpigotLocale def;
    public final Map<String, Component<SpigotLocale, BaseComponent[]>> translated = new HashMap<>();

    public ExtraSpigotLocale(LocaleData data, DefaultSpigotLocale def) {
        this.data = data;
        this.def = def;
    }

    /**
     * @deprecated use default constructor and handle parse failure properly
     */
    @Deprecated
    public ExtraSpigotLocale(LocaleData data, DefaultSpigotLocale def, Stream<String> additional) {
        this(data, def);
        try {
            extendLocale(additional);
        } catch (TranslationParseFailure e) {
            System.err.println("Some lines failed to parse:");
            for(var line : e.getFailedLines()){
                System.err.println(line.getLine());
                line.getFailure().printStackTrace();
            }
        }
    }

    public void extendLocale(Stream<String> additional) throws TranslationParseFailure {
        List<FailedLine> failedLines = new ArrayList<>();
        PoParser.poParse(additional).forEach((k, v) -> {
            try {
                translated.put(k, new SpigotComponentParser(v).parse());
            } catch (Exception e) {
                failedLines.add(new FailedLine(v, e));
            }
        });
        if(!failedLines.isEmpty()){
            throw new TranslationParseFailure(failedLines);
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

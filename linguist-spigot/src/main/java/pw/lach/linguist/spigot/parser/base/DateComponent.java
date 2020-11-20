package pw.lach.linguist.spigot.parser.base;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import pw.lach.linguist.spigot.SpigotLocale;
import pw.lach.linguist.spigot.parser.SpigotComponent;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class DateComponent implements SpigotComponent {
    DateTimeFormatter dateTimeFormatter;
    int slot = -1;

    @Override
    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public void setProperty(String value) {
        dateTimeFormatter = DateTimeFormatter.ofPattern(value, Locale.ENGLISH);
    }

    @Override
    public void validate() {
        if (slot == -1) {
            throw new IllegalStateException("Date slot is not set");
        }
    }

    @Override
    public BaseComponent[] localize(SpigotLocale locale, Object[] input) {
        var format = dateTimeFormatter == null ? locale.getData().getDefaultDateFormat() : dateTimeFormatter;
        var date = (TemporalAccessor) input[slot];
        return new BaseComponent[]{new TextComponent(format.format(date))};
    }
}

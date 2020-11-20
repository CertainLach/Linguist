package pw.lach.linguist.spigot.parser.base;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import pw.lach.linguist.spigot.SpigotLocale;
import pw.lach.linguist.spigot.parser.SpigotComponent;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberComponent implements SpigotComponent {
    ThreadLocal<DecimalFormat> decimalFormat;
    int slot = -1;

    @Override
    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public void setProperty(String value) {
        decimalFormat = ThreadLocal.withInitial(() -> new DecimalFormat(value, new DecimalFormatSymbols(Locale.ENGLISH)));
    }

    @Override
    public void validate() {
        if (slot == -1) {
            throw new IllegalStateException("Date slot is not set");
        }
    }

    @Override
    public BaseComponent[] localize(SpigotLocale locale, Object[] input) {
        var format = decimalFormat == null ? locale.getData().getDecimalFormat() : decimalFormat;
        var num = (double) input[slot];
        return new BaseComponent[]{new TextComponent(format.get().format(num))};
    }
}

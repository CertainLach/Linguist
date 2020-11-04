package pw.lach.linguist.spigot.data;

import java.util.Locale;

// TODO: Locale/NumberFormat
public interface LocaleData {
    String getName();

    int choosePluralForm(int amount);
}

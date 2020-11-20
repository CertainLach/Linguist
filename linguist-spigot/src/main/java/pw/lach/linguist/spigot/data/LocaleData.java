package pw.lach.linguist.spigot.data;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// TODO: Locale/NumberFormat
public interface LocaleData {
    String getName();

    int choosePluralForm(int amount);

    Locale getLocale();

    DateTimeFormatter getDefaultDateFormat();

    ThreadLocal<NumberFormat> getDecimalFormat();
}

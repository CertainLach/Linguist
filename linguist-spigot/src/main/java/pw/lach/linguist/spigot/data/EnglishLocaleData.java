package pw.lach.linguist.spigot.data;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class EnglishLocaleData implements LocaleData {
    private static final Locale LOCALE = new Locale("en", "US");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yy K:mm a", LOCALE);
    private static final ThreadLocal<NumberFormat> DECIMAL_FORMAT = ThreadLocal.withInitial(() -> DecimalFormat.getInstance(LOCALE));

    @Override
    public String getName() {
        return "en_US";
    }

    @Override
    public int choosePluralForm(int amount) {
        return Math.abs(amount) == 1 ? 1 : 2;
    }

    @Override
    public Locale getLocale() {
        return LOCALE;
    }

    @Override
    public DateTimeFormatter getDefaultDateFormat() {
        return DATE_FORMAT;
    }

    @Override
    public ThreadLocal<NumberFormat> getDecimalFormat() {
        return DECIMAL_FORMAT;
    }
}

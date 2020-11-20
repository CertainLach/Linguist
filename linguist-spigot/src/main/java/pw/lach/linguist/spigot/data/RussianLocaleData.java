package pw.lach.linguist.spigot.data;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RussianLocaleData implements LocaleData {
    private static final Locale LOCALE = new Locale("ru", "RU");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm", LOCALE);
    private static final ThreadLocal<NumberFormat> DECIMAL_FORMAT = ThreadLocal.withInitial(() -> DecimalFormat.getInstance(LOCALE));

    @Override
    public String getName() {
        return "ru_RU";
    }

    @Override
    public int choosePluralForm(int amount) {
        amount = Math.abs(amount) % 100;
        int amountDec = amount % 10;
        return amount > 10 && amount < 20 ? 5 : amountDec > 1 && amountDec < 5 ? 2 : amountDec == 1 ? 1 : 5;
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

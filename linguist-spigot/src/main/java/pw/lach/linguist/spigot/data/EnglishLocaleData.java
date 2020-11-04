package pw.lach.linguist.spigot.data;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class EnglishLocaleData implements LocaleData {
    @Override
    public String getName() {
        return "en-uk";
    }

    @Override
    public int choosePluralForm(int amount) {
        return Math.abs(amount) == 1 ? 1 : 2;
    }
}

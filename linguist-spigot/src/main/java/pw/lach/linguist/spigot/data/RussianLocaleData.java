package pw.lach.linguist.spigot.data;

public class RussianLocaleData implements LocaleData {
    @Override
    public String getName() {
        return "ru-ru";
    }

    @Override
    public int choosePluralForm(int amount) {
        amount = Math.abs(amount) % 100;
        int amountDec = amount % 10;
        return amount > 10 && amount < 20 ? 5 : amountDec > 1 && amountDec < 5 ? 2 : amountDec == 1 ? 1 : 5;
    }
}

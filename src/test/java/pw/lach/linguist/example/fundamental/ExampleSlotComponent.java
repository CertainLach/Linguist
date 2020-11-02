package pw.lach.linguist.example.fundamental;

import pw.lach.linguist.fundamental.SlotComponent;
import pw.lach.linguist.example.ExampleLocale;

public class ExampleSlotComponent extends SlotComponent<ExampleLocale, String> {
    public ExampleSlotComponent(int slot) {
        super(slot);
    }

    @Override
    public String localize(ExampleLocale locale, Object[] input) {
        return input[slot].toString();
    }
}

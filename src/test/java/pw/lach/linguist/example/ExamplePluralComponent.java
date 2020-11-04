package pw.lach.linguist.example;

import pw.lach.linguist.Component;
import pw.lach.linguist.example.ExampleLocale;

public class ExamplePluralComponent implements Component<ExampleLocale, String> {
    boolean showCount = true;
    int valueSlot = -1;
    String form1;
    String form2;

    @Override
    public void setSlot(int slot) {
        valueSlot = slot;
    }

    @Override
    public void setNamedProperty(String name, String value) {
        switch (name) {
            case "1":
                form1 = value;
                break;
            case "2":
                form2 = value;
                break;
            default:
                Component.notSupported(name + " property");
        }
    }

    @Override
    public void enableFeature(String name) {
        switch (name) {
            case "hideCount":
                showCount = false;
                break;
            default:
                Component.notSupported(name + " feature");
        }
    }

    public void validate() {
        if (valueSlot == -1) {
            throw new IllegalStateException("Value slot is not set");
        } else if (form1 == null || form2 == null) {
            throw new IllegalStateException("Not all forms are set");
        }
    }

    @Override
    public String localize(ExampleLocale locale, Object[] input) {
        int value = (int) input[valueSlot];
        String out = "";
        if (showCount) {
            out += value + " ";
        }
        if (Math.abs(value) == 1) {
            out += form1;
        } else {
            out += form2;
        }
        return out;
    }
}

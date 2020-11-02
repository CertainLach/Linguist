package pw.lach.linguist.example;

import pw.lach.linguist.IComponent;
import pw.lach.linguist.example.ExampleLocale;
import java.util.List;

public class ExamplePluralComponent implements IComponent<ExampleLocale, String> {
    boolean showCount = true;
    int valueSlot = -1;
    String form1;
    String form2;

    @Override
    public void setProperty(String name, String value) {
        switch (name) {
            case "1":
                form1 = value;
                break;
            case "2":
                form2 = value;
                break;
            default:
                throw new IllegalStateException("Property not supported: " + name);
        }
    }

    @Override
    public void setPropertySlot(String name, int slot) {
        switch (name) {
            case "value":
                valueSlot = slot;
                break;
            default:
                throw new IllegalStateException("Slot not supported: " + name);
        }
    }

    @Override
    public void enableProperty(String name) {
        switch (name) {
            case "hideCount":
                showCount = false;
                break;
            default:
                throw new IllegalStateException("Property not supported: " + name);
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
    public void setChildren(List<IComponent<ExampleLocale, String>> components) {
        throw new IllegalStateException("No children supported");
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

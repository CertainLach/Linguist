package pw.lach.linguist.example;

import pw.lach.linguist.ComponentParser;
import pw.lach.linguist.IComponent;
import pw.lach.linguist.example.fundamental.*;
import java.util.List;

public class ExampleComponentParser extends ComponentParser<ExampleLocale, String> {
    public ExampleComponentParser(char[] string) {
        super(string);
    }

    public ExampleComponentParser(String string) {
        super(string);
    }

    public IComponent<ExampleLocale, String> parse() {
        return super.parse();
    }

    protected IComponent<ExampleLocale, String> componentByName(String name) {
        switch (name) {
            case "plural":
                return new ExamplePluralComponent();
            default:
                throw new IllegalStateException("Unknown component " + name);
        }
    }

    protected ExampleStringComponent fundamentalString(String str) {
        return new ExampleStringComponent(str);
    }

    protected ExampleSlotComponent fundamentalSlot(int slot) {
        return new ExampleSlotComponent(slot);
    }

    protected ExampleListComponent fundamentalList(List<IComponent<ExampleLocale, String>> components) {
        return new ExampleListComponent(components);
    }

}

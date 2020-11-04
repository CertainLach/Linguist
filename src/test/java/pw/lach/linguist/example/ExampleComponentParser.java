package pw.lach.linguist.example;

import org.intellij.lang.annotations.Language;
import pw.lach.linguist.ComponentParser;
import pw.lach.linguist.Component;
import pw.lach.linguist.example.fundamental.*;
import java.util.List;

public class ExampleComponentParser extends ComponentParser<ExampleLocale, String> {
    public ExampleComponentParser(@Language("Linguist") String string) {
        super(string);
    }

    public Component<ExampleLocale, String> parse() {
        return super.parse();
    }

    protected Component<ExampleLocale, String> componentByName(String name) {
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

    protected ExampleListComponent fundamentalList(List<Component<ExampleLocale, String>> components) {
        return new ExampleListComponent(components);
    }

}

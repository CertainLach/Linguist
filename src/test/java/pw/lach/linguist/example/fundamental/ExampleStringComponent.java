package pw.lach.linguist.example.fundamental;

import pw.lach.linguist.fundamental.StringComponent;
import pw.lach.linguist.example.ExampleLocale;

public class ExampleStringComponent extends StringComponent<ExampleLocale, String> {
    public ExampleStringComponent(String string) {
        super(string);
    }

    @Override
    public String localize(ExampleLocale locale, Object[] input) {
        return string;
    }
}

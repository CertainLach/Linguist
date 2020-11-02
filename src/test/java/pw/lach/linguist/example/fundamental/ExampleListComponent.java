package pw.lach.linguist.example.fundamental;

import pw.lach.linguist.IComponent;
import pw.lach.linguist.fundamental.ListComponent;
import pw.lach.linguist.example.ExampleLocale;
import java.util.List;

public class ExampleListComponent extends ListComponent<ExampleLocale, String> {
    public ExampleListComponent(List<IComponent<ExampleLocale, String>> list) {
        super(list);
    }

    @Override
    public String localize(ExampleLocale locale, Object[] input) {
        String out = "";
        for (var component : list) {
            out += component.localize(locale, input);
        }
        return out;
    }
}

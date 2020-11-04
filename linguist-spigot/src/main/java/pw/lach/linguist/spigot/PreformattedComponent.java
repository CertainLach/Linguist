package pw.lach.linguist.spigot;

import org.intellij.lang.annotations.Language;

public class PreformattedComponent implements ToComponent {
    final String name;
    final Object[] data;

    public PreformattedComponent(String name, Object[] data) {
        this.name = name;
        this.data = data;
    }

    public static PreformattedComponent t(@Language("Linguist") String name, Object... data) {
        return new PreformattedComponent(name, data);
    }

    @Override
    public PreformattedComponent toComponent() {
        return this;
    }
}

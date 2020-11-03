package pw.lach.linguist;

import java.util.ArrayList;
import java.util.List;

import pw.lach.linguist.fundamental.ListComponent;
import pw.lach.linguist.fundamental.SlotComponent;
import pw.lach.linguist.fundamental.StringComponent;

public abstract class ComponentParser<L, O> {
    private final char[] string;
    private int offset = 0;
    private int slot = 0;

    protected ComponentParser(char[] string) {
        this.string = string;
    }

    protected ComponentParser(String input) {
        this(input.toCharArray());
    }

    protected abstract IComponent<L, O> componentByName(String name);

    protected abstract StringComponent<L, O> fundamentalString(String str);

    protected abstract SlotComponent<L, O> fundamentalSlot(int slot);

    protected abstract ListComponent<L, O> fundamentalList(List<IComponent<L, O>> components);

    protected IComponent<L, O> parse() {
        return fundamentalList(takeComponents());
    }

    private String takeComponentName() {
        int start = offset;
        while (peek() != '{' && peek() != '}' && peek() != '.' && peek() != ' ') {
            skip();
        }
        return new String(string, start, offset - start);
    }

    private String takePropertyName() {
        int start = offset;
        while (peek() != '\'' && peek() != '{' && peek() != '}' && peek() != ' ' && peek() != '.') {
            skip();
        }
        return new String(string, start, offset - start);
    }

    private String takeStringProperty() {
        if (take() != '\'') {
            throw new IllegalStateException("Strings should start with \"'\"");
        }
        int start = offset;
        while (peek() != '\'') {
            skip();
        }
        var out = new String(string, start, offset - start);
        skip();
        return out;
    }

    private int takeSlot() {
        if (take() != '{') {
            throw new IllegalStateException("Slots should start with \"{\"");
        }
        if (peek() == '}') {
            skip();
            return slot++;
        }
        int result = takeInt();
        if (take() != '}') {
            throw new IllegalStateException("Slots should end with \"}\"");
        }
        return result;
    }

    private StringComponent<L, O> takeComponentString() {
        int start = offset;
        while (!isEnd() && peek() != '{' && peek() != '}') {
            skip();
        }
        return fundamentalString(new String(string, start, offset - start));
    }

    private List<IComponent<L, O>> takeComponents() {
        var out = new ArrayList<IComponent<L, O>>();
        while (!isEnd() && peek() != '}') {
            switch (peek()) {
                case '{':
                    out.add(takeComponent());
                    break;
                default:
                    out.add(takeComponentString());
                    break;
            }
        }
        return out;
    }

    private IComponent<L, O> takeComponent() {
        if (Character.isDigit(peek(1))) {
            return fundamentalSlot(takeSlot());
        }
        if (take() != '{') {
            throw new IllegalStateException("Components should start with \"{\"!");
        }
        var name = takeComponentName();
        var component = componentByName(name);

        switch (peek()) {
            case '{':
                var value = takeSlot();
                component.setSlot(value);
                break;
        }

        while (peek() == '.') {
            skip();
            var prop = takePropertyName();
            switch (peek()) {
                case '\'':
                    var value = takeStringProperty();
                    component.setProperty(prop, value);
                    break;
                case '{':
                    var slot = takeSlot();
                    component.setNamedSlot(prop, slot);
                    break;
                case '}':
                case '.':
                case ' ':
                    component.enableFeature(prop);
                    break;
                default:
            }
        }
        var ch = take();
        if (ch == '}') {
            component.validate();
            return component;
        }
        if (ch != ' ') {
            throw new IllegalStateException("Component description should end with either \" \" or \"}\"");
        }
        skip();
        component.setChildren(takeComponents());
        if (take() != '}') {
            throw new IllegalStateException("Component description with children should end with \"}\"");
        }
        component.validate();
        return component;
    }

    private int takeInt() {
        int value = 0;
        while (Character.isDigit(peek())) {
            value *= 10;
            value += Character.digit(take(), 10);
        }
        return value;
    }

    private void skip() {
        offset++;
    }

    private char take() {
        return string[offset++];
    }

    private char peek(int ahead) {
        return string[offset + ahead];
    }

    private char peek() {
        return string[offset];
    }

    private boolean isEnd() {
        return string.length == offset;
    }
}

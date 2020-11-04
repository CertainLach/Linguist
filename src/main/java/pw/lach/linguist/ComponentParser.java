package pw.lach.linguist;

import pw.lach.linguist.fundamental.ListComponent;
import pw.lach.linguist.fundamental.SlotComponent;
import pw.lach.linguist.fundamental.StringComponent;

import java.util.ArrayList;
import java.util.List;

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

    protected abstract Component<L, O> componentByName(String name);

    protected abstract StringComponent<L, O> fundamentalString(String str);

    protected abstract SlotComponent<L, O> fundamentalSlot(int slot);

    protected abstract ListComponent<L, O> fundamentalList(List<Component<L, O>> components);

    protected Component<L, O> parse() {
        return fundamentalList(takeComponents());
    }

    private String takeComponentName() {
        int start = offset;
        while (peek() != '\'' && peek() != '{' && peek() != '}' && peek() != ' ' && peek() != '.') {
            skip();
        }
        return new String(string, start, offset - start);
    }

    private String takePropertyName() {
        return takeComponentName();
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

    private StringComponent<L, O> takeComponentString() {
        int start = offset;
        while (!isEnd() && peek() != '{' && peek() != '}') {
            skip();
        }
        return fundamentalString(new String(string, start, offset - start));
    }

    private List<Component<L, O>> takeComponents() {
        var out = new ArrayList<Component<L, O>>();
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

    private Component<L, O> takeComponent() {
        if (take() != '{') {
            throw new IllegalStateException("Components should start with \"{\"!");
        }
        if (Character.isDigit(peek())) {
            int value = takeInt();
            return fundamentalSlot(value);
        } else if (peek() == '#') {
            skip();
            var components = takeComponents();
            return fundamentalList(components);
        }
        var name = takeComponentName();
        var component = componentByName(name);

        try {
            switch (peek()) {
                case '{':
                    var value = takeComponent();
                    if (value instanceof SlotComponent) {
                        component.setSlot(((SlotComponent<L, O>) value).slot);
                    } else {
                        component.setComponent(value);
                    }
                    break;
                case '\'':
                    component.setProperty(takeStringProperty());
                    break;
            }

            while (peek() == '.') {
                skip();
                var prop = takePropertyName();
                switch (peek()) {
                    case '\'': {
                        var value = takeStringProperty();
                        component.setNamedProperty(prop, value);
                        break;
                    }
                    case '{': {
                        var value = takeComponent();
                        if (value instanceof SlotComponent) {
                            component.setNamedSlot(prop, ((SlotComponent<L, O>) value).slot);
                        } else {
                            component.setComponent(value);
                        }
                        break;
                    }
                    case '}':
                    case '.':
                    case ' ': {
                        component.enableFeature(prop);
                        break;
                    }
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
        } catch (RuntimeException e) {
            throw new IllegalStateException("While parsing " + name + " at " + location(), e);
        }
    }

    private String location() {
        return "\"" + new String(string) + "\":" + offset;
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

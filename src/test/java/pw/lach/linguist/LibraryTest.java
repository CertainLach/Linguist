package pw.lach.linguist;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import pw.lach.linguist.example.ExampleComponentParser;
import pw.lach.linguist.example.ExampleLocale;

public class LibraryTest {
    @Test
    public void onlyString() {
        Component<ExampleLocale, String> component = new ExampleComponentParser("Hello world!").parse();
        assertEquals(component.localize(null, null), "Hello world!");
    }

    @Test
    public void pluralBasic() {
        Component<ExampleLocale, String> component = new ExampleComponentParser("Hello world!").parse();
        assertEquals(component.localize(null, new Object[] { 1 }), "There is 1 tomato");
        assertEquals(component.localize(null, new Object[] { 2 }), "There is 2 tomatos");
    }

    @Test
    public void pluralHiddenCount() {
        Component<ExampleLocale, String> component = new ExampleComponentParser("There is {plural{}.1'tomato'.2'tomatos'.hideCount}").parse();
        assertEquals(component.localize(null, new Object[] { 1 }), "There is tomato");
        assertEquals(component.localize(null, new Object[] { 2 }), "There is tomatos");
    }
}

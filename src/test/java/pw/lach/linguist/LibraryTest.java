package pw.lach.linguist;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import pw.lach.linguist.*;
import pw.lach.linguist.example.*;

public class LibraryTest {
    @Test
    public void onlyString() {
        IComponent<ExampleLocale, String> component = new ExampleComponentParser("Hello world!").parse();
        assertEquals(component.localize(null, null), "Hello world!");
    }

    @Test
    public void pluralBasic() {
        IComponent<ExampleLocale, String> component = new ExampleComponentParser(
                "There is {plural.1'tomato'.2'tomatos'.value{}}").parse();
        assertEquals(component.localize(null, new Object[] { 1 }), "There is 1 tomato");
        assertEquals(component.localize(null, new Object[] { 2 }), "There is 2 tomatos");
    }

    @Test
    public void pluralHiddenCount() {
        IComponent<ExampleLocale, String> component = new ExampleComponentParser(
                "There is {plural.1'tomato'.2'tomatos'.hideCount.value{}}").parse();
        assertEquals(component.localize(null, new Object[] { 1 }), "There is tomato");
        assertEquals(component.localize(null, new Object[] { 2 }), "There is tomatos");
    }
}

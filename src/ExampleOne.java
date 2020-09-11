import java.util.ArrayList;
import java.util.List;

// Why Generics?
public class ExampleOne {
    // old way (Java 5 and below)
    public String oldWay() {
        List basket = new ArrayList(); // "raw" types, produce warnings
        basket.add("Apple");
        String nameOfFruit = (String) basket.get(0); // might throw ClassCastException at Runtime, we never know

        return nameOfFruit;
    }
    public String newWay() {
        List<String> basket = new ArrayList<>();
        basket.add("Apple");
        String nameOfFruit = basket.get(0); // will not throw ClassCastException at Runtime

        return nameOfFruit;
    }
}

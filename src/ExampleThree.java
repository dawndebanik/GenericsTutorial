import models.Fruit;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

class Utils {

    // bounded generic type
    // without this, we cannot invoke a comparison method
    public static <T extends Comparable<T>> T findMin(List<T> list) {
        T lowest = list.get(0);
        for (T current : list) {
            if (current.compareTo(lowest) < 0) {
                lowest = current;
            }
        }

        return lowest;
    }
}

public class ExampleThree {
    public static void main(String[] args) {
        List<Integer> wholeNumbers = Arrays.asList(4, 2, 9);
        List<Double> fractionalNumbers = Arrays.asList(3.0, 2.0, 3.9);
        List<String> strings = Arrays.asList("very", "generic", "stuff");

        System.out.println(Utils.findMin(wholeNumbers));  // 2
        System.out.println(Utils.findMin(fractionalNumbers));  // 2.0
        System.out.println(Utils.findMin(strings));  // "generic"

    }
}

// example of multiple bounds
class MultipleBounds<T extends Cloneable & Serializable & Readable> {}

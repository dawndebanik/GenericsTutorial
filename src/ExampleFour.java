import models.Apple;
import models.Fruit;
import models.Orange;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Wildcards 🦁🃏 ?
 * 1. Upper-bounded Wildcards
 * 2. Lower-bounded Wildcards
 * 1. Unbounded Wildcards
 */
public class ExampleFour {
    private static class Bowl<T> {
        T value;

        public void setValue(T newVal) {
            this.value = newVal;
        }

        public T getValue() {
            return value;
        }
    }

    // PECS
    public static void main(String[] args) {
        demoCovariance();
        demoContravariance();
        demoInvariance();
    }

    // upper bounded wildcard
    private static void demoCovariance() {
        // producer

        Bowl<? extends Fruit> bowl = new Bowl<>();
//        bowl.setValue(new Apple("red-apple")); // no c-time guarantee that container is compatible with apple
//        bowl.setValue(new Fruit("exotic-fruit")); // no c-time guarantee that container is compatible with fruit

        Fruit someFruit = bowl.getValue(); // 👌
//        Apple importedApple = bowl.getValue(); // no c-time guarantee that it contains apples
    }

    // lower bounded wildcard
    private static void demoContravariance() {
        // consumer

        Bowl<? super Fruit> bowl = new Bowl<>();
        bowl.setValue(new Apple("red-apple")); // 👌
        bowl.setValue(new Fruit("exotic-fruit")); // 👌
        bowl.setValue(new Orange("orange-orange")); // 👌
//        bowl.setValue(new Object()); // 💣

//        Fruit someFruit = bowl.getValue(); // wrong
//        Apple importedApple = bowl.getValue(); // 💣
        Object something = bowl.getValue(); // 👌
    }

    // unbounded wildcard
    private static void demoInvariance() {
        // neither producer nor consumer

        Bowl<?> bowl = new Bowl<>();
//        bowl.setValue(new Object());  // 💣
        Object something = bowl.getValue(); // 👌
    }
}

class ExampleFourUsages {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        Number sum = useUpperBoundedWCToFindSum(integerList);

        Set<Fruit> fruits = Set.of(new Orange("jaffa-orange"), new Apple("kashmiri-apple"));
        useLowerBoundedWBToAppend(fruits);

        Integer size = useUnboundedWCToCount(fruits);
    }

    private static Number useUpperBoundedWCToFindSum(List<? extends Number> list) {
        double sum = 0;

        for (Number number : list) {
            sum += number.doubleValue();
        }

        return sum;
    }

    private static void useLowerBoundedWBToAppend(Set<? super Apple> apples) {
        Apple redApple = new Apple("red-apple");
        Apple greenApple = new Apple("green-apple");

        apples.add(redApple);
        apples.add(greenApple);
    }

    private static Integer useUnboundedWCToCount(Set<?> setToCount) {
        int count = 0;


        for (Object ignored : setToCount) {
            count ++;
        }

        return count;
    }
}

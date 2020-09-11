import models.Fruit;

class Basket {
    private final Object[] stuff;
    Basket(Object... stuff) {
        this.stuff = stuff;
    }

    Object[] getStuff() {
        return stuff;
    }
}

class BetterBasket<T> {
    private final T[] stuff;
    BetterBasket(T... stuff) {
        this.stuff = stuff;
    }

    T[] getStuff() {
        return stuff;
    }
}

public class ExampleTwo {

    private static final Fruit apple = new Fruit("Apple");
    private static final Fruit orange = new Fruit("Orange");

    public static void main(String[] args) {
        Integer someNumber = Integer.MAX_VALUE;
        Basket myMorningBasket = new Basket(apple, orange, someNumber); // this is possible

        // ...somewhere else in the codebase
        for (Object thingInside: myMorningBasket.getStuff()) {
            Fruit currentFruit = (Fruit) thingInside; // unsafe cast 💣 and will break in RT
        }

        betterCode();
    }

    private static void betterCode() {
        BetterBasket<Fruit> myMorningBasket = new BetterBasket<>(apple, orange);
            // no longer possible to add Integer (or anything else)

        // ...somewhere else in the codebase
        for (Object thingInside: myMorningBasket.getStuff()) {
            Fruit currentFruit = (Fruit) thingInside; // perfectly 👌 at RT
            System.out.println(currentFruit.name);
        }
    }
}
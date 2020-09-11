import models.Fruit;

// Type Erasure ✏️
public class ExampleFive {

    // erasure for boundless type T
    private static class NotBounded<T> {
        T member;

        NotBounded(T member) {
            this.member = member;
        }
    }

    private static class NotBoundedAfterErasure {
        Object member;

        NotBoundedAfterErasure(Object member) {
            this.member = member;
        }
    }

    // erasure for bounded type T
    private static class Bounded<T extends Fruit> {
        T member;

        Bounded(T member) {
            this.member = member;
        }
    }

    private static class BoundedAfterErasure {
        Fruit member;

        BoundedAfterErasure(Fruit member) {
            this.member = member;
        }
    }
}

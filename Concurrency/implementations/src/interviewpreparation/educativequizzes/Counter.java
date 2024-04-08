package interviewpreparation.educativequizzes;

public class Counter {
    ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 0);

    public Counter() {
        counter.set(10);
    }

    void increment() {
        counter.set(counter.get() + 1);
    }
}

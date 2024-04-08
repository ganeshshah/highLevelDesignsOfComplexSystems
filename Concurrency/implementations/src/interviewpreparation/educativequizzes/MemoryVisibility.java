package interviewpreparation.educativequizzes;

public class MemoryVisibility {

    int myvalue = 2;
    boolean done = false;
    void thread1() {
        while (!done);
        System.out.println(myvalue);
    }
    void thread2() {
        myvalue = 5;
        done = true;
    }

    public static void main(String[] args) throws InterruptedException {
        MemoryVisibility mv = new MemoryVisibility();
        Thread thread1 = new Thread(() -> {
            mv.thread1();
        });
        Thread thread2 = new Thread(() -> {
            mv.thread2();
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}

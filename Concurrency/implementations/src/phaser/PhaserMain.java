package phaser;

import java.util.concurrent.Phaser;

public class PhaserMain {


    public static void main(String[] args) {



        Phaser ph = new Phaser(1);

        new Thread(new LongRunningAction("thread-1", ph)).start();
        new Thread(new LongRunningAction("thread-2", ph)).start();
        new Thread(new LongRunningAction("thread-3", ph)).start();

        System.out.println(ph.getPhase());
        ph.arriveAndAwaitAdvance();
        //System.out.println(ph.getPhase());
        new Thread(new LongRunningAction("thread-4", ph)).start();
        new Thread(new LongRunningAction("thread-5", ph)).start();
        ph.arriveAndAwaitAdvance();
        System.out.println(ph.getPhase());
        //ph.arriveAndDeregister();
        //System.out.println(ph.getPhase());

        new Thread(new LongRunningAction("thread-4", ph)).start();
        new Thread(new LongRunningAction("thread-5", ph)).start();
        ph.arriveAndAwaitAdvance();
        System.out.println(ph.getPhase());

    }
}

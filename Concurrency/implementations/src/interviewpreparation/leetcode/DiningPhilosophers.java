package interviewpreparation.leetcode;

class DiningPhilosophers {

    private volatile Object[] forks = new Object[5];

    public DiningPhilosophers() {
        for(int i=0;i<forks.length;i++)
            forks[i] = new Object();
    }


    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        int left = philosopher;
        int right = (philosopher+1)%5;


        if(philosopher == 4)
            eatPhilosopher(left,right,pickLeftFork,pickRightFork,eat,putLeftFork,putRightFork);
        else
            eatPhilosopher(right, left, pickLeftFork,pickRightFork,eat,putLeftFork,putRightFork);

    }


    void eatPhilosopher(Object fork1, Object fork2,Runnable pickLeftFork,
                        Runnable pickRightFork,
                        Runnable eat,
                        Runnable putLeftFork,
                        Runnable putRightFork){
        synchronized(fork1){
            synchronized(fork2){
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putRightFork.run();
                putLeftFork.run();
            }
        }
    }
}
package compareandswap;

import java.util.concurrent.atomic.AtomicBoolean;

public class CompareAndSwapLock {

    private AtomicBoolean locked = new AtomicBoolean(false);

    public void unlock() {
        this.locked.set(false);
    }

    public void lock() {
        while(!this.locked.compareAndSet(false, true)) {
            // busy wait - until compareAndSet() succeeds
        }
    }
}
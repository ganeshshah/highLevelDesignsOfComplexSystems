package threadpool;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory  implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread th = new Thread(r);
        return th;
    }
}

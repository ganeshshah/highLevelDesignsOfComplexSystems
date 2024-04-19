package interviewpreparation.mustdoquestions.initrequestclose;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InitRequestCloseMain {

    public static void main(String[] args) throws InterruptedException {

        InitRequestClose resource = new InitRequestClose();


        ExecutorService executor = Executors.newFixedThreadPool(5);



        for(int i=0;i<10;i++){
            executor.submit(() -> {
                resource.request();
            });
        }

        executor.submit(() -> {
            resource.init();
        });

       // Thread.sleep(1000);


        executor.submit(() -> {
            try {
                resource.close();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });



        for(int i=0;i<10;i++){
            executor.submit(() -> {
                resource.request();
            });
        }

        executor.submit(() -> {
            try {
                resource.close();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });



    }
}

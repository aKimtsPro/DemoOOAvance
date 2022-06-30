package bstorm.akimts.oo.avance.demo.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DemoWaitNotify {

    public static void main(String[] args) {

        Demo d = new Demo();
        Thread t1 = new Thread(() -> {
            try {
                d.startWaiting();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(5000);
                d.stopWaiting();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

    }

}

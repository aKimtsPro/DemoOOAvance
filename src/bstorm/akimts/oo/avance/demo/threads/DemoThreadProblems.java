package bstorm.akimts.oo.avance.demo.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DemoThreadProblems {

    public static void main(String[] args) {

        Compte c1 = new Compte(); // 0
        Compte c2 = new Compte(); // 0
        ExecutorService executor = Executors.newFixedThreadPool(2);


        Future<?> exec1 = executor.submit( () -> {
            for (int i = 0; i < 100_000; i++)
                c1.gagnerArgent(1);
        } );
        Future<?> exec2 = executor.submit( () -> {
            for (int i = 0; i < 100_000; i++)
                c1.perdreArgent(1);
        }  );
        executor.shutdown();

        try {
            exec1.get();
            exec2.get();

            System.out.println("argent sur mon compte 1 : " + c1.getSolde());
            System.out.println("argent sur mon compte 2 : " + c2.getSolde());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }




    }

}

package bstorm.akimts.oo.avance.demo.threads;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class DemoThreads {

    public static void main(String[] args) {



        Thread th = new Thread( () -> {
            int tempsCuisine = new Random().nextInt(3, 8);
            System.out.println( Thread.currentThread().getName() + " - Je fais à manger");
            try {
                Thread.sleep(tempsCuisine * 1000L);
            } catch (InterruptedException e) {
                System.out.println("Le thread a été interrompu");;
            }
            System.out.println( Thread.currentThread().getName() +" - A table" );
        });


        System.out.println("J'ai faim");
        th.start();
        // "Est ce que je peux me mettre à table?" ou "? - A table" | reponse: "? - A table"


        System.out.println("Je commence à attendre...");
        try {
            th.join();
            th.start(); // impossible de lancer 2x le même thread
        } catch (InterruptedException e) {
            System.out.println("Le main a été interrompu pendant l'attente");
        }
        System.out.println("J'ai fini d'attendre, je mange"); // "? - A table?" ou "Je mange"


        System.out.println("--- ExecutorService ---");
        ExecutorService service = Executors.newFixedThreadPool(3);
//        service.submit(() -> System.out.println(Thread.currentThread().getName() + " - action 1"));
//        service.submit(() -> System.out.println(Thread.currentThread().getName() + " - action 2"));

        Future<?> future0 = service.submit(() -> System.out.println(Thread.currentThread().getName() + " - action 3"));
        Future<Integer> future = service.submit( () -> new Random().nextInt(1,101) );

        try {
            future0.get(); // comme join
            int random = future.get(); // comme join mais renvoi une valeur
            System.out.println(random);
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        System.out.println("--- invokeAny");
        Callable<Long> call = () -> {
            long l = new Random().nextLong(9001);
            System.out.println(Thread.currentThread().getName() +" > debut de l'attente de " + l + "ms");
            Thread.sleep( l );
            System.out.println(Thread.currentThread().getName() +" > fin de l'attente de " + l + "ms");
            return l;
        };
        try {
            long tempsMinAttendu = service.invokeAny( List.of( call, call, call, call ) );
            // les threads du pool prennent en chergent les différents actions dès que possible.
            // Renvoi le premier resultat obtenu des Callables. Annule les autres actions quand un rslt est trouvé
            System.out.println(tempsMinAttendu);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("--- fin invokeAny");

    }

}

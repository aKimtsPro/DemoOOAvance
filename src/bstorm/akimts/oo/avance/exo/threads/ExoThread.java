package bstorm.akimts.oo.avance.exo.threads;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class ExoThread {

    static Carcasse carcasse;
    static Moteur moteur;


    public static void main(String[] args) {

        // Où placer les composants afin d'y avoir accès dans le Thread main
//        Thread thCarcasse = new Thread(() -> {
//            // simulation du temps nécessaire
//            long tempsNecessaire = new Random().nextInt(2, 5) * 1000L;
//            try {
//                Thread.sleep( tempsNecessaire );
//                carcasse = new Carcasse( TypeCarcasse.CITADINE );
//                System.out.println("Carcasse construite en " + tempsNecessaire + "ms" );
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread thMoteur = new Thread(() -> {
//            // simulation du temps nécessaire
//            long tempsNecessaire = new Random().nextInt(2, 5) * 1000L;
//            try {
//                Thread.sleep(tempsNecessaire);
//                moteur = new Moteur(100);
//                System.out.println("Moteur construit en " + tempsNecessaire + "ms" );
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });


//        thCarcasse.start();
//        thMoteur.start();

        ExecutorService executor = Executors.newFixedThreadPool( 2 );
        Future<Moteur> fMoteur = executor.submit( () -> {
            long tempsNecessaire = new Random().nextInt(1, 5) * 1000L;
            Thread.sleep(tempsNecessaire);
            System.out.println(Thread.currentThread().getName() +" > Moteur construit en " + tempsNecessaire + "ms" );
            return new Moteur(100);
        } );
        Future<Carcasse> fCarcasse = executor.submit( () -> {
            long tempsNecessaire = new Random().nextInt(1, 5) * 1000L;
            Thread.sleep(tempsNecessaire);
            System.out.println(Thread.currentThread().getName() +" > Carcasse construite en " + tempsNecessaire + "ms" );
            return new Carcasse( TypeCarcasse.CITADINE );
        } );

//        fCarcasse.cancel(true);

        try {
            Voiture v = new Voiture( fMoteur.get(3, TimeUnit.SECONDS), fCarcasse.get(3, TimeUnit.SECONDS)); // le get attend le resultat
            System.out.println( v );
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        catch (CancellationException ex){
            System.out.println("La construction de la carcasse a été annulée");
            System.out.println("Je ne peux pas créer ma voiture");
        } catch (TimeoutException e) {
            System.out.println("Le moteur ou la carcasse n'ont pas été créés dans le temps imparti");
            fMoteur.cancel(true);
            fCarcasse.cancel(true);
            System.out.println("pas besoin de");
        }
        System.out.println("Fin du programme");

    }

}

package bstorm.akimts.oo.avance.demo.threads;

import java.util.Random;

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
        } catch (InterruptedException e) {
            System.out.println("Le main a été interrompu pendant l'attente");
        }
        System.out.println("J'ai fini d'attendre, je mange"); // "? - A table?" ou "Je mange"




    }

}

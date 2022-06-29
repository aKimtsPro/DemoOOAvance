package bstorm.akimts.oo.avance.demo.threads;

import java.util.Random;
import java.util.Scanner;

public class InterruptionDemo {


    static long variable = 0;
    static boolean stop = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Toute les 250ms, ajoute de 0 à 2 dans une variables (récupérable hors du thread)
        Thread th = new Thread(() -> {
            Random rdm = new Random();
            try {
                while( !stop ) {
                    Thread.sleep(250);
                    variable += rdm.nextInt(3);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        th.start();
        sc.nextLine();
        stop = true;

        try {
            th.join();
            System.out.println( variable );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

package bstorm.akimts.oo.avance.exo.threads;

public class ExoThread {

    public static void main(String[] args) {

        // Où placer les composants afin d'y avoir accès dans le Thread main
        Thread thCarcasse = new Thread(() -> {
            // simulation du temps nécessaire
            // creation d'une carcasse;
        });
        Thread thMoteur = new Thread(() -> {
            // simulation du temps nécessaire
            // creation d'un moteur;
        });


        thCarcasse.start();
        thMoteur.start();

        // Seulement une fois que les composants sont créés
        Voiture v = new Voiture( moteur, carcasse );

    }

}

package bstorm.akimts.oo.avance.demo.threads;

public class RaceCondition {

    static long variable;

    public static void main(String[] args) throws InterruptedException {

        Runnable action = () -> {
            for (int i = 0; i < 5_000_000; i++) {
                variable++;
            }
        };
        Thread th1 = new Thread( action );
        Thread th2 = new Thread( action );

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        // variable++ <=> variable = variable + 1;
        // 1 - recupération de la valeur de variable
        // 2 - ajout de 1 à cette valeur
        // 3 - l'affection de la nouvelle valeur à la variable

        System.out.println( variable );
        // l'interaction parallèle de 2 threads avec la meme ressource peut donner des problèmes
        // de type Race Condition dans lesquels l'ordre de l'execution va changer le comportement
        // du programme



    }

}

package bstorm.akimts.oo.avance.exo.threads;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.*;

public class ExoExecutor {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<String>> futures = new ArrayList<>();

        Callable<String> callable = ExoExecutor::generateWord;
        for (int i = 0; i < 6; i++) {
            futures.add( executor.submit( callable ) );
        }
        executor.shutdown(); // Stop l'acceptation de nouvelles taches
//        executor.shutdownNow(); // Stop toutes les taches même courantes

        // daemon - thread qui continue son existence après la fin du programme

        StringBuilder builder = new StringBuilder();
        futures.forEach( f -> {
            try {
                builder.append(f.get()).append(' ');
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        String phrase = builder.toString();
        System.out.println(phrase);

    }

    /**
     * Crée un mot fait de lettre minuscules aléatoires (entre 2 et 6 lettres)
     * @return String, le mot généré
     */
    public static String generateWord(){

        System.out.println(Thread.currentThread().getName() + " > commence la génération d'un mot");
        Random random = new Random();
        int length = random.nextInt(2,7);
        StringBuilder builder = new StringBuilder();
//        String mot = "";
        for (int j = 0; j < length; j++) {
//            mot += ((char) random.nextInt(97,123));
            builder.append( ((char) random.nextInt(97,123)) );
        }

        String motGenere = builder.toString();
        System.out.println(Thread.currentThread().getName() + " > mot généré: "+ motGenere);
        return motGenere;

    }

}

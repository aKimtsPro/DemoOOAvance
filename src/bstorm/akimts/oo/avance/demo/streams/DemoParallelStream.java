package bstorm.akimts.oo.avance.demo.streams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DemoParallelStream {

    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();

        long time = System.currentTimeMillis();
        IntStream.rangeClosed(1, 1_000_000)
                .parallel()
                .forEach( list::add );
        System.out.println("temps d'execution en parallèle: " + (System.currentTimeMillis() - time) + "ms");


        list = new LinkedList<>();
        time = System.currentTimeMillis();
        IntStream.rangeClosed(1, 1_000_000)
                .forEach( list::add );
        System.out.println("temps d'execution en séquentiel: " + (System.currentTimeMillis() - time) + "ms");

        // Les problèmes du parallèlisme:

        // - 1 : ne pas utiliser quand l'ordre est important
        // - 2 : s'assurer qu'il y a bien un gain de pref. (il existe un cout a payer pour se mettre en parallèle (Overhead))
        // - 3 : il est déconseillé de l'utiliser dans une application utilisant déjà beaucoup le parallèlisme ( container web par exemple )

        // A utiliser seulement si il y a un besoin réel de gain de perf.


    }

}

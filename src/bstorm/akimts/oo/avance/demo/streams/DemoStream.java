package bstorm.akimts.oo.avance.demo.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DemoStream {

    public static void main(String[] args) {

        List<Personne> personnes = new ArrayList<>();
        personnes.add(new Personne("Luc", 37));
        personnes.add(new Personne("Marie", 17));
        personnes.add(new Personne("Dominique", 67));

        // Definition 'simpliste' du Stream<T>:
        // Suite d'éléments de type T sur lesquels on peut faire des opérations
        // les unes après les autres.

        // Utilisation des Stream:
        // 1) Declaration des operations intermédiaires
        // 2) L'appel à l'opération terminale

        // On peut reconnaitre les opération intermédiaires au fait qu'elles renvoient des
        // Stream. Les opérations terminales ne renvoient jamais de Stream.


        System.out.println("1 - Utilisation basique du Stream\n");

        personnes.stream() // Stream<Personne> 1
                .filter( p -> p.getAge() >= 40 ) // Stream<Personne> 2 // intermédiaire
                .map( Personne::getNom ) // Stream<String> 3 // intermédiaire
                .forEach( System.out::println ); // terminale

        System.out.println();

        // Attention au IllegalStateExcetion !
        System.out.println("2 - Ne pas réutiliser un même Stream\n");

        try {
            Stream<Personne> stream = personnes.stream();
            stream.filter( p -> p.getAge() >= 40 );
            stream.map(p -> p.getNom());
        }
        catch (IllegalStateException ex){
            System.out.println("Je ne peux pas faire 2 opérations sur un même Stream");
        }

        // Ceci fonctionne car on n'execute jamais 2 opé sur le même Stream
        Stream<Personne> stream = personnes.stream();
        stream = stream.filter( p -> p.getAge() >= 40 );
        Stream<String> stream3 = stream.map( Personne::getNom ); // Stream<String>
        stream3.forEach( System.out::println );

        // Chaque opération intermédiaire crée un nouveau Stream (sur lequel je peut donc faire une opération)
        System.out.println();


        System.out.println("3 - L'ordre d'execution des opérations");

        // Inverser l'ordre des opérations peut changer le résultat final
        // => Ordre des opérations est important
        System.out.println("Je cherche à écrire en console des nouvelles personnes basées sur celle de la liste");
        System.out.println("Je n'affiche que les nouvelles personnes de plus de 40ans (i: intermédiaire, t: terminale)");
        personnes.stream()
                .map((p) -> {
                    // transforme chaque personne en une nouvelle personne de meme
                    // nom mais 2x plus vieille
                    System.out.println("i > mappage sur " + p);
                    return new Personne(p.getNom(), p.getAge()*2);
                })
                .filter((p) -> {
                    System.out.println("i > filterage sur " + p);
                    return p.getAge() >= 40;
                })
                .forEach(p -> System.out.println("t > ecriture de " + p));

        System.out.println("\nJe cherche à ecrire en console des nouvelles personnes basées sur les personnes de la liste de plus de 40ans");
        personnes.stream()
                .filter((p) -> {
                    System.out.println("i > filterage sur " + p);
                    return p.getAge() >= 40;
                })
                .map((p) -> {
                    // transforme chaque personne en une nouvelle personne de meme
                    // nom mais 2x plus vieille
                    System.out.println("i > mappage sur " + p);
                    return new Personne(p.getNom(), p.getAge()*2);
                })
                .forEach(p -> System.out.println("t > ecriture de " + p));

        System.out.println("\nATTENTION: Le traitement ne s'exécute qu'à l'appel de l'opération terminale");
        personnes.stream()
                .filter((p) -> {
                    System.out.println("i > filterage sur " + p);
                    return p.getAge() >= 40;
                })
                .map((p) -> {
                    // transforme chaque personne en une nouvelle personne de meme
                    // nom mais 2x plus vieille
                    System.out.println("i > mappage sur " + p);
                    return new Personne(p.getNom(), p.getAge()*2);
                });


        System.out.println();


        System.out.println("4 - Les opérations intermédiaires");

        personnes.stream()
                // elimine des éléments de la stream - les opérations suivantes ne seront pas executées sur un rejeté
                .filter( p -> p.getNom().length() <= 50 )
                // permet de transformer un élément en autre chose de n'imp. quel type T, renvoi une Stream<T>
                .map( p -> p.getNom() + '-'+ p.getAge() )
                // rejete les éléments en duplicata (comparaison d'adresse)
                .distinct();
    }


}

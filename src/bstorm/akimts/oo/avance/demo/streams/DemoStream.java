package bstorm.akimts.oo.avance.demo.streams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.function.Consumer;
import java.util.stream.*;

public class DemoStream {

    public static void main(String[] args) {

        List<Personne> personnes = new ArrayList<>();

        Personne luc = new Personne("Luc", 37);
        Personne marie = new Personne("Marie", 17);
        Personne dominique = new Personne("Dominique", 67);


        personnes.add(luc);
        personnes.add(marie);
        personnes.add(dominique);

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
                // permet de transformer un élément en autre chose de n'imp. quel type T, renvoi un Stream<T>
                .map( p -> p.getNom() + '-'+ p.getAge() )
                // mapToXXX permet de transformer 1 élément en un autre de type primitif. Renvoi un XXXStream (mapToInt renvoi un IntSteam)
                // Les Stream spécialisés dans la gestion des primitifs possèdes des méthodes adaptées
 //                .mapToInt( Personne::getAge )
                //-----
                // rejete les éléments en duplicata (comparaison d'adresse)
                .distinct()
                // Fait qq chose avec chaque element - forEach mais en intermédiaire
                .peek( System.out::println )
                // rejete les X premiers elements
                .skip(1)
                // s'arrete au X premiers elements non rejeté
                .limit(1)
                // tranformer 1 éléments en PLUSIEURS éléments de n'importe quel type T. Produit un Stream<T>
                // Il existe aussi un variance flatMapToXXX transformant en plusieurs élément de type primitif. Renvoi un XXXStream(flatMapToDouble renvoi un DoubleStream)
                .flatMap( nom -> Stream.of( new Personne(nom, 10), new Personne(nom, 10) ))
                // trie les éléments dans un ordre croissant (considéré comme inférieur -> supérieur) sont un facon de comparer renseigné
                // Je peux utiliser la surcharge sans param si les éléments comparés implementent l'interface Comparable
                .sorted( (p1, p2) -> p1.getAge() - p2.getAge() );

        System.out.println();


        System.out.println("5 - Opérations terminales");

        // forEach applique un Consumer sur chaque élément
        personnes.stream()
                .forEach( System.out::println );

        // regroupe les éléments dans un tableau d'Object (surcharge permet de donner le type)
        Object[] tab =  personnes.stream()
                .toArray();

        // Compte le nombre d'éléments
        long nbrPersDePlus40Ans = personnes.stream()
                .filter( p -> p.getAge() >= 40 )
                .count();

        // Verifie si au moins un élément est conforme au Predicate
        boolean presentPlus40 = personnes.stream() // true
                .anyMatch( p -> p.getAge() > 40 );
        // Verifie si tous les éléments sont conforme au Predicate
        boolean tousPlus40 = personnes.stream() // false
                .allMatch( p -> p.getAge() > 40 );
        // Verifie si aucun élément n'est conforme au Predicate
        boolean aucunPlus40 = personnes.stream() // false
                .noneMatch( p -> p.getAge() > 40 );
        // si le noneMatch renvoi false, le anyMatch renvoi true (si le Predicate est le même)

        // PErmet de recupérer n'importe quel élément (TRES souvent le premier)
        Optional<Personne> nimpQuelPersonne = personnes.stream().findAny();
        // Trouve TOUJOURS la première personne (sauf si vide)
        Optional<Personne> premierePersonne = personnes.stream().findFirst();
        // ATTENTION OPTIONAL


        // rassemble en une list
        List<Personne> plusDe20Ans = personnes.stream()
                        .filter( p -> p.getAge() >= 20)
                        .collect( Collectors.toList() );

        // former une chaine de caractère representant la concatenation des noms des personne
        String concatenation = personnes.stream()
                .map( Personne::getNom )
                // Le reduce permet de reduire les éléments du Stream en un seul élément
                // Il demande en paramètre un BinaryOperator dont
                // - le premier paramètre sera une valeur accumulée,
                // - le 2e sera le prochain élément considéré
                // - le retour sera la nouvelle valeur accumulée
                // Il existe des surcharge de reduce avec ou sans premier paramètre
                // indiquant ou pas la valeur de base d'accumulation.
                // Si le paramètre est présent => renvoi directement une valeur
                // Sinon => renvoi un Optional de la valeur
                .reduce("", (acc, element) -> {
                    System.out.println("t > reduce :"+acc+" - " + element);
                    return acc + element;
                } );

        System.out.println(concatenation);



        System.out.println();
        System.out.println("5b - Demo Optional");

//        Personne personne = nimpQuelPersonne.orElse( dominique );
//        Personne personne = nimpQuelPersonne.orElseGet( () -> new Personne("Personne instanciée seulement si vide", 0) );
//nouvelle personne

//        Personne personne = nimpQuelPersonne.get(); // soit recupère la valeur soit lance une exception NoSuchElementException
//        Personne personne = nimpQuelPersonne.orElseThrow(); // pareil que get()
        Personne personne = nimpQuelPersonne.orElseThrow( () -> new RuntimeException("L'élément n'a pas été trouvé") ); // Pareil que get sauf qu'on choisi l'exception



        System.out.println(personne);
        System.out.println();

        System.out.println("6 - Création de Stream");
        Random rdm = new Random();

        Stream<Integer> s = Stream.of(1,2,3,4,5,6,7,8,9); // limité
        s = Stream.generate( rdm::nextInt ); // illimité
        s = Stream.iterate( 0, (i) -> i+1 ); // illimité


        // concat produit un limité si les 2 params sont stream limité,
        // illimité sinon
        s = Stream.concat(s, Stream.of(0)); // illimité
        s = Stream.concat(s.limit(10), Stream.of(0)); // limité

        s = Stream.empty(); // limité
        s = Stream.ofNullable(10); // limité peu importe le param

        IntStream intStream = IntStream.range(1, 100);
        intStream = IntStream.rangeClosed(1, 100);
        LongStream longStream = LongStream.range(1,100);
        longStream = LongStream.rangeClosed(1,100);


        // Certains objets permettent de générer des streams.
        // C'est le cas des Collections et BufferedReader par exemple.
        try(BufferedReader bf = new BufferedReader( new FileReader("resources/new_file.txt"))) {
            bf.lines()
//                    .map(chaine -> /* creer une personne sur base de la ligne */)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // exemple de generate
        List<String> nomsPossibles = List.of("Paul", "Luc", "Bob", "John", "Medhi", "Marie", "Claire", "Lucie");

        Stream<Personne> persStream = Stream.generate( () -> {
            return new Personne(
                    nomsPossibles.get(rdm.nextInt(nomsPossibles.size()) ),
                    rdm.nextInt(100)
            );
        });

        // Attention au Stream illimité
        Personne pers = persStream.filter( p -> p.getNom().equals("Bob") )
                .findFirst() // très proba que ca ne foire pas / mais possibilité que ca foire
                .get();
        // Les Stream illimités peuvent produire des boucles infinies => OutOfMemoryException

        List<Integer> ints = List.of(1,2,3);
        Stream<Integer> streamDeListe = ints.stream();

        IntStream streamInt = IntStream.range(1, 101);
        streamInt = IntStream.rangeClosed(1, 100);


        // generer des personnes, trouver la première qui s'appelle Bob

        System.out.println();
        System.out.println("7 - Stream paralleles");

        long beforeMillis = System.currentTimeMillis();
        int sum = s.parallel()       // Stream<Integer>
                .mapToInt( i -> i ) // IntStream
                .sum();
        long afterMillis = System.currentTimeMillis();

        System.out.println("Temps d'execution = " + (afterMillis-beforeMillis) +"ms");


        // BONUS
//        personnes.stream()
//                // produit un Stream<String>
//                .flatMap(personne -> Arrays.stream( personne.getNom().split("") ))
//                .forEach(System.out::println);

//        personnes.stream()
//        // produit un Stream<Character>
//        .flatMap(
//                personne -> {
//                    char[] tab = personne.getNom().toCharArray();
//                    // la ligne suivante tranforme mon tableau en un Stream de char
//                    return CharBuffer.wrap(tab).chars().mapToObj(ch -> (char)ch);
//                }
//        ).forEach(System.out::println);

//        personnes.stream()
//                // L'interface Comparator possède un certain nombre de méthodes static
//                // utiles dans le cadre des comparaison
//                .sorted(Comparator.comparingInt( Personne::getAge ).reversed())
//                .forEach(System.out::println);

    }


}

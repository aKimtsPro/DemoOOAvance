package bstorm.akimts.oo.avance.demo.lambdas;

import bstorm.akimts.oo.avance.demo.streams.Personne;
import bstorm.akimts.oo.avance.exo.CompetitionImpl;
import bstorm.akimts.oo.avance.exo.sportifs.Coureur;
import bstorm.akimts.oo.avance.exo.sportifs.Sportif;

import java.time.LocalDate;
import java.util.function.*;

public class DemoLambda {

    public static void main(String[] args) {

        Sportif sportif = new Sportif("dubois", "luc", LocalDate.now()) {
            @Override
            public int performer() {
                return 0;
            }
        };

        Vol vol = new Vol() { // peut être transformée en lambda
            @Override
            public boolean voler(int d, int h) {
                System.out.println("je vole");
                System.out.println("Je vole encore");
                return true;
            }

//            @Override
//            public void atterir() {
//                System.out.println("j'atteri");
//            }
        };

        // On peut écrire une lambda si:
        // - sa cible est une interface
        // - sa cible possède une seule mèthode DEVANT être modifiée (non 'default' ou 'static')

        // param:
        // - 1 seul: pas beson de ()
        // - 1 typé: parenthèses obligatoires
        // - 2: parenthèse obligatoires

        // instructions:
        // - 1 instruction non return: {} non nécessaires => ; non nécessaire
        // - 1 instruction return: {} ; non nécessaires. Si pas d'{} => pas de 'return'
        // - 2 instructions : {} ; nécessaires

        vol = (dist,h) -> (dist + h) % 2 == 0;

        Vol vol2 = vol::voler;

        System.out.println( vol.voler(5,10) ); // false
        System.out.println( vol2.voler(5,10) ); // false
        System.out.println(vol == vol2); // false


        Proie p1 = () -> System.out.println("1 est chassé");
        Proie p2 = () -> System.out.println("2 est chassé");
        p1.estChasse();

        // On affecte à p3 une référence d'une nouvelle instance de classe anonyme implementant Proie
        // dont le comportement pour la méthode 'estChasse' sera le même que celui de p1
        p2 = () -> p1.estChasse(); // 0 p -> pas de retour
        p2.estChasse(); // 1 est chassé

        p2 = Proie::decrire;  // 0p (static pas besoin d'instance) -> pas de retour
        p2.estChasse(); // des proies

        // On affecte à c1 ine référence d'une nouvelle instance de classe anonyme implementant Chasseur
        // dont le comportement pour 'chasser' sera l'appel de 'estChasser' sur la Proie en parametre
        Chasseur c1 = Proie::estChasse;// 1p(Proie car non static) -> pas de retour
//        c1 = new Chasseur() {
//            @Override
//            public void chasser(Proie aChasser) {
//                aChasser.estChasse();
//            }
//        };
        c1.chasser(p2); // des proies

        c1.chasser( () -> System.out.println("je chasse") ); // doit écrire "je chasse"

//        vol.atterir();
////        vol.truc(); // impossible
//        Vol.truc();

        Coureur c2 = new Coureur("","", null, 0,0){

            @Override
            public String getPrenom() {
                return "truc"+super.getPrenom();
            }

        };


        Consumer<String> consumer = System.out::println;
        consumer = consumer.andThen( (chaine) -> System.out.println(chaine.toUpperCase()) );

        LocalDate ld = LocalDate.now();
        Supplier<LocalDate> supplier = LocalDate::now;
        String s = ( "MA CHAINE".concat( "ma chaine".toUpperCase() ) ).toLowerCase();
        s = "MA CHAINE";
        Function<String, String> function = s::concat;

        // toLowerCase : String -> String
        // toUpperCase : String -> String
        // concat      : String -> String

        // toUpperCase      =>    (concat)      =>   toLowerCase
        // String -> String----String -> String----String -> String

        function = function.compose( String::toUpperCase );
        function = function.andThen( String::toLowerCase );

        System.out.println( function.apply( "ma chaine" ) );
        // ma chaine   - avant toUpperCase
        // MA CHAINE   - apres toUpperCase
        // MA CHAINE   - avant concat
        // MA CHAINEMA CHAINE - apres concat
        // MA CHAINEMA CHAINE - avant toLowerCase
        // ma chainema chaine - après toLowerCase

        UnaryOperator<String> op = (chaine) -> {
            chaine = chaine.toUpperCase();
            chaine = "MA CHAINE".concat(chaine);
            return chaine.toLowerCase();
        };

        BiFunction<String, String, String> biFunc = String::concat;
        BinaryOperator<String> biOp = String::concat;

        Predicate<CompetitionImpl<Sportif>> predicate = CompetitionImpl::isTerminee;
        predicate = predicate.and((compet) -> compet.getGagnants().size() > 1)
                .or(sportifCompetition -> sportifCompetition.getLimiteParticipant() < 100)
                .negate();


        Runnable run = () -> System.out.println("ok");


        BiFunction<String,Integer, Personne> biFunc2 = Personne::new;
        BiFunction<String,Integer, String> composition = biFunc2.andThen( Personne::getAge )
                .andThen( (entier) -> "Age de la personne: "+entier);

        String agePersonne = composition.apply("luc", 50);
        System.out.println( agePersonne );

    }

}

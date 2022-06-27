package bstorm.akimts.oo.avance.demo.lambdas;

import bstorm.akimts.oo.avance.exo.sportifs.Coureur;
import bstorm.akimts.oo.avance.exo.sportifs.Sportif;

import java.time.LocalDate;

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




    }

}

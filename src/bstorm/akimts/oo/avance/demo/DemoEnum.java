package bstorm.akimts.oo.avance.demo;

import java.util.Scanner;

public class DemoEnum {

    public static void main(String[] args) {

//        DifficulteMastermind difficulte = DifficulteMastermind.MOYEN;
//        difficulte.getCouleurs()[0] = "autre couleur";


//        StringBuilder concat = new StringBuilder();
//        for (int i = 0; i < 1_000_000; i++) {
//            concat.append('a');
//        }
//        String rslt = concat.toString();

//        boolean invalid = true;
        Jeu jeu = null;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Selectionnez votre mode de jeu:" +
                    "\n- FACILE" +
                    "\n- MOYEN" +
                    "\n- DIFFICILE");
            String diff = sc.nextLine();

            try {
                jeu = new Jeu(DifficulteMastermind.valueOf(diff));
//                invalid = false;
            }
            catch (IllegalArgumentException ex){
                System.out.println("Valeur invalide");
            }

        } while( jeu == null );

        System.out.println(jeu.getDifficulte());



    }

}

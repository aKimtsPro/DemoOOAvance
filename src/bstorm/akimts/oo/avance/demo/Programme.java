package bstorm.akimts.oo.avance.demo;

import bstorm.akimts.oo.avance.demo.exceptions.InterfaceBanque;

public class Programme {

    public static void main(String[] args){
        new InterfaceBanque().start();
//        System.out.println("debut du programme");
//        String saisPas = null;
//        if(saisPas != null)
//            methode(saisPas);
//
//
//
//        try {
//            int[] tab = {1,2,3};
//            System.out.println(tab[2]);
//
//            chechedOrUnchecked();
//        }
//        catch ( RuntimeException ex ){
//            System.out.println( "unchecked " + ex.getMessage() );
//        }
//        catch ( Exception ex ){
//            System.out.println( "checked " + ex.getMessage() );
//        }
//
//        System.out.println("fin du programme");

    }


    /**
     * ecrit en console le nombre de caractères
     *
     * @param chaine ne doit pas être null
     * @throws NullPointerException si la chaine est nulle
     */
    public static void methode(String chaine) {

        System.out.println("debut de methode");
        throw new RuntimeException();
//        System.out.println("fin de la methode");
    }

    public static void chechedOrUnchecked() throws Exception {
        System.out.println("salut");
        throw new Exception("checked");
    }

}

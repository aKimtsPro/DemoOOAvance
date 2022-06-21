package bstorm.akimts.oo.avance.demo;

import bstorm.akimts.oo.avance.demo.generics.*;

public class DemoGenerics {

    public static void main(String[] args) {

        Boite<Chocolat, Poid> boiteDeChocolat = new Boite<>();
        boiteDeChocolat.getMesure().setPoid(100);
        boiteDeChocolat.getMesure().setUnite("gr");

    }

}

package bstorm.akimts.oo.avance.exo;

import bstorm.akimts.oo.avance.exo.exceptions.EtatCompetitionException;
import bstorm.akimts.oo.avance.exo.sportifs.Coureur;
import bstorm.akimts.oo.avance.exo.sportifs.CoureurSprint;
import bstorm.akimts.oo.avance.exo.sportifs.Sportif;

import java.util.ArrayList;
import java.util.Set;
import static bstorm.akimts.oo.avance.exo.Localisation.*;

public class Programme {

    public static void main(String[] args) {

        CompetitionImpl<Coureur> compet = new CompetitionImpl<>(REGIONAL, "nom", Coureur.class);
        compet.inscrire(new Coureur("","",null, 0,0));

        compet.abonner(()-> System.out.println("La competition est terminée"));

        compet.lancer();
//        compet.getClassements().forEach(coureur-> System.out.println(coureur)); // meme chose que ci dessous
        compet.getClassements().forEach(System.out::println);
//        compet.sauvegarder();



    }

}

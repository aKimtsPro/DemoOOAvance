package bstorm.akimts.oo.avance.exo;

import bstorm.akimts.oo.avance.exo.exceptions.EtatCompetitionException;
import bstorm.akimts.oo.avance.exo.sportifs.Coureur;
import bstorm.akimts.oo.avance.exo.sportifs.CoureurSprint;
import bstorm.akimts.oo.avance.exo.sportifs.Sportif;

import java.util.ArrayList;

public class Programme {

    public static void main(String[] args) {

        CompetitionImpl<Coureur> compet = new CompetitionImpl<>(5);
        ArrayList<CoureurSprint> coureurSprints = new ArrayList<>();

        CompetitionImpl<Sportif> compet2 = new CompetitionImpl<>();
        compet2.inscrire(new ArrayList<Sportif>());


        CompetitionImpl<CoureurSprint> compet3 = new CompetitionImpl<>();
        compet3.transfertParticipants(compet);
//        compet.transfertParticipants(compet3); // impossible tous les Coureur ne sont pas de CoureurSprint



        try {
            compet.inscrire(coureurSprints);
            compet.lancer();
            compet.getClassements().forEach(System.out::println);
        }
        catch(EtatCompetitionException ex) {
            ex.printStackTrace();
        }

    }

}

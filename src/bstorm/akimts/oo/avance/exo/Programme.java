package bstorm.akimts.oo.avance.exo;

import bstorm.akimts.oo.avance.demo.annotation.NonCompetitif;
import bstorm.akimts.oo.avance.exo.exceptions.EtatCompetitionException;
import bstorm.akimts.oo.avance.exo.sportifs.Coureur;
import bstorm.akimts.oo.avance.exo.sportifs.CoureurSprint;
import bstorm.akimts.oo.avance.exo.sportifs.Sportif;

import java.util.ArrayList;
import java.util.Set;
import static bstorm.akimts.oo.avance.exo.Localisation.*;

public class Programme {

    public static void main(String[] args) {

        CompetitionImpl<Coureur> compet = new CompetitionImpl<>(REGIONAL);
        ArrayList<CoureurSprint> coureurSprints = new ArrayList<>();

        CompetitionImpl<Sportif> compet2 = new CompetitionImpl<>(NATIONAL);
        compet2.inscrire(new ArrayList<Sportif>());

        CompetitionImpl<CoureurSprint> compet3 = new CompetitionImpl<>(INTERNATIONAL);
        compet3.transfertParticipants(compet);
//        compet.transfertParticipants(compet3); // impossible tous les Coureur ne sont pas de CoureurSprint



        Set<CoureurSprint> coureurSprints1 = compet.getOfType(CoureurSprint.class);

        CompetitionImpl<Sportif> competition = CompetitionImpl.fusionner(compet, compet3);

        CoureurSprint sprint = new CoureurSprint("","",null, 0, 0);
        compet3.inscrire(sprint);

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

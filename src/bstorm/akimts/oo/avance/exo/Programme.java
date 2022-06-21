package bstorm.akimts.oo.avance.exo;

import bstorm.akimts.oo.avance.exo.exceptions.EtatCompetitionException;

import java.util.List;

public class Programme {

    public static void main(String[] args) {

        CompetitionImpl compet = new CompetitionImpl(5);
        compet.lancer();

        try {
            compet.inscrire(new Sportif("", "", null));
        }
        catch(EtatCompetitionException ex) {
            ex.printStackTrace();
        }

    }

}

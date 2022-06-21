package bstorm.akimts.oo.avance.exo;

import bstorm.akimts.oo.avance.exo.exceptions.EtatCompetitionException;

import java.util.List;

public class Programme {

    public static void main(String[] args) {

        CompetitionImpl compet = new CompetitionImpl(5);

        try {
            compet.inscrire(new Sportif("", "", null));
            compet.inscrire(new Sportif("", "", null));
            compet.inscrire(new Sportif("", "", null));
            compet.inscrire(new Sportif("", "", null));
            compet.lancer();
            compet.getClassements().forEach(System.out::println);
        }
        catch(EtatCompetitionException ex) {
            ex.printStackTrace();
        }

    }

}

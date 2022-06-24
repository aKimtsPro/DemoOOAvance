package bstorm.akimts.oo.avance.exo.sportifs;

import bstorm.akimts.oo.avance.demo.annotation.NonCompetitif;
import bstorm.akimts.oo.avance.exo.Localisation;

import java.time.LocalDate;

@NonCompetitif({Localisation.REGIONAL, Localisation.NATIONAL})
public class CoureurSprint extends Coureur{
    public CoureurSprint(String nom, String prenom, LocalDate dateNaiss, int vitesseMoyenne, int poid) {
        super(nom, prenom, dateNaiss, vitesseMoyenne, poid);
    }
}

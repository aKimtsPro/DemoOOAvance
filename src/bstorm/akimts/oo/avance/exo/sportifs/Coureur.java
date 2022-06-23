package bstorm.akimts.oo.avance.exo.sportifs;

import java.time.LocalDate;
import java.util.Random;

public class Coureur extends Sportif{

    private int vitesseMoyenne;
    private int poid;

    public Coureur(String nom, String prenom, LocalDate dateNaiss, int vitesseMoyenne, int poid) {
        super(nom, prenom, dateNaiss);
        this.vitesseMoyenne = vitesseMoyenne;
        this.poid = poid;
    }

    public int getVitesseMoyenne() {
        return vitesseMoyenne;
    }

    public void setVitesseMoyenne(int vitesseMoyenne) {
        if(vitesseMoyenne > 13 || vitesseMoyenne < 8)
            throw new IllegalArgumentException("vitesse entre 8 et 13");

        this.vitesseMoyenne = vitesseMoyenne;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        if(poid > 200 || poid < 60)
            throw new IllegalArgumentException("poid entre 60 et 200");

        this.poid = poid;
    }

    @Override
    public int performer() {
        Random rdm = new Random();
        return vitesseMoyenne / poid * (110 + rdm.nextInt(21));
    }
}

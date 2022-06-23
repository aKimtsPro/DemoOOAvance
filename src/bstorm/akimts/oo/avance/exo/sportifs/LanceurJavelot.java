package bstorm.akimts.oo.avance.exo.sportifs;

import java.time.LocalDate;
import java.util.Random;

public class LanceurJavelot extends Sportif{

    private int force;
    private int anneeExp;

    public LanceurJavelot(String nom, String prenom, LocalDate dateNaiss, int force, int anneeExp) {
        super(nom, prenom, dateNaiss);
        this.force = force;
        this.anneeExp = anneeExp;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        if(force > 50 || force < 30)
            throw new IllegalArgumentException("force entre 30 et 50");

        this.force = force;
    }

    public int getAnneeExp() {
        return anneeExp;
    }

    public void setAnneeExp(int anneeExp) {
        if(anneeExp > 20 || anneeExp < 0)
            throw new IllegalArgumentException("exp entre 0 et 20");

        this.anneeExp = anneeExp;
    }

    @Override
    public int performer() {
        Random rdm = new Random();
        double rslt = force + anneeExp *  (70 + rdm.nextInt(21) ) / 100.0;
        return (int)rslt;
    }
}

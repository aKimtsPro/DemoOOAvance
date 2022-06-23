package bstorm.akimts.oo.avance.exo.sportifs;

import java.time.LocalDate;
import java.util.Random;

public class Judoka extends Sportif{

    private int puissanceProjection = 40;

    public Judoka(String nom, String prenom, LocalDate dateNaiss, int puissanceProjection) {
        super(nom, prenom, dateNaiss);
        this.setPuissanceProjection( puissanceProjection );
    }

    @Override
    public int performer() {
        Random rdm = new Random();
        return puissanceProjection + (rdm.nextInt(41) - 20);
    }

    public int getPuissanceProjection() {
        return puissanceProjection;
    }

    public void setPuissanceProjection(int puissanceProjection) {
        if(puissanceProjection > 80 || puissanceProjection < 40)
            throw new IllegalArgumentException("la puissance de projection doit être entre 40 et 80");

        this.puissanceProjection = puissanceProjection;
    }
}

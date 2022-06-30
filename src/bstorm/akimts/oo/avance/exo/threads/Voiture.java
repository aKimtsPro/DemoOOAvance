package bstorm.akimts.oo.avance.exo.threads;

public class Voiture {

    private Carcasse carcasse;
    private  Moteur moteur;

    public Voiture( Moteur moteur, Carcasse carcasse) {
        this.carcasse = carcasse;
        this.moteur = moteur;
    }

    public Carcasse getCarcasse() {
        return carcasse;
    }

    public void setCarcasse(Carcasse carcasse) {
        this.carcasse = carcasse;
    }

    public Moteur getMoteur() {
        return moteur;
    }

    public void setMoteur(Moteur moteur) {
        this.moteur = moteur;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "carcasse=" + carcasse +
                ", moteur=" + moteur +
                '}';
    }
}

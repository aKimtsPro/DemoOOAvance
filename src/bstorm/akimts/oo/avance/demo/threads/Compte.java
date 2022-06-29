package bstorm.akimts.oo.avance.demo.threads;

public class Compte {

    private long solde;

    public long getSolde() {
        return solde;
    }

    public void gagnerArgent(long montant) {
        solde += montant;
    }
}

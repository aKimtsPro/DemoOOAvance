package bstorm.akimts.oo.avance.demo.threads;

public class Compte {

    private long solde;
    private final Object key = new Object();
    // Peut servir de clef.
    // Si je mets la key en static, il ne sera pas possible d'ajouter de l'argent
    // dans 2 comptes différent à l'exact meme moment.
    // Si la clef n'est pas static, ceci sera possible

    public long getSolde() {
        return solde;
    }

    public void gagnerArgent(long montant) { // solde = 1, montantT1 = 1, montantT2 = 1

        if( montant > 0 ){
//        System.out.println(Thread.currentThread().getName() + " > demande la clef");
            synchronized ( this ) {
//            System.out.println(Thread.currentThread().getName() + " > a obtenu la clef");
                solde += montant;
//            System.out.println(Thread.currentThread().getName() + " > rend la clef");
            }
        }

    }

    public void perdreArgent(long montant) {

        if( montant > 0 ){
            // Plusieurs portions de code ne pouvant pas être exécuté en parallèle doivent
            // être protégés par la même clef
            synchronized ( this ){
                solde -= montant;
            }
        }


    }

    // La clef implicite du mot clef synchronized est toujours 'this'
    public synchronized void reset(){
        solde = 0;
    }
}

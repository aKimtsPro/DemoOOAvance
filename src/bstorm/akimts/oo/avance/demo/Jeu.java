package bstorm.akimts.oo.avance.demo;

public class Jeu {

    private DifficulteMastermind difficulte;

    public Jeu(DifficulteMastermind difficulte) {
        this.difficulte = difficulte;
    }

    public DifficulteMastermind getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(DifficulteMastermind difficulte) {
        this.difficulte = difficulte;
    }
}

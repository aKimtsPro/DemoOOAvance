package bstorm.akimts.oo.avance.demo;

public enum DifficulteMastermind {

    FACILE(new String[]{"red", "green", "blue"}, 3, 20),
    MOYEN(new String[]{"red", "green", "blue", "yellow", "white"}, 10),
    DIFFICILE(new String[]{"red", "green", "blue", "yellow", "white", "black"}, 6, 8);

    private final String[] couleurs;
    private final int nbrCases;
    private final int nbrTentatives;

    DifficulteMastermind(String[] couleurs, int nbrTentatives) {
        this.couleurs = couleurs;
        this.nbrTentatives = nbrTentatives;
        this.nbrCases = 4;
    }

    DifficulteMastermind(String[] couleurs, int nbrCases, int nbrTentatives) {
        this.couleurs = couleurs;
        this.nbrCases = nbrCases;
        this.nbrTentatives = nbrTentatives;
    }

    public String[] getCouleurs() {
        return couleurs.clone();
    }

    public int getNbrCases() {
        return nbrCases;
    }

    public int getNbrTentatives() {
        return nbrTentatives;
    }
}

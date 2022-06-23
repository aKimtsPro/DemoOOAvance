package bstorm.akimts.oo.avance.exo;

import bstorm.akimts.oo.avance.exo.sportifs.Sportif;

import java.util.Set;


// Cette interface me permet de rester dans le cadre de l'énoncé
public interface Competition<S extends Sportif> {

    void lancer();
    void inscrire(S sportif);
    void desinscrire(S sportif);
    Set<S> getGagnants();
    boolean isTerminee();

    int getLimiteParticipant();

}

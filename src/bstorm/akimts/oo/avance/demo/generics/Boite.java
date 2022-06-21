package bstorm.akimts.oo.avance.demo.generics;

import java.util.Map;

public class Boite<C extends Contenu, M extends Mesure> {

    private C contenu;
    private M mesure;

    public C getContenu() {
        return contenu;
    }

    public void setContenu(C contenu) {
        this.contenu = contenu;
    }

    public M getMesure() {
        return mesure;
    }

    public void setMesure(M mesure) {
        this.mesure = mesure;
    }
}

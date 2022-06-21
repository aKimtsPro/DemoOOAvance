package bstorm.akimts.oo.avance.demo.generics;

public class Quantite implements Mesure {

    private int qtt;

    public Quantite(int qtt) {
        this.qtt = qtt;
    }

    public int getQtt() {
        return qtt;
    }

    public void setQtt(int qtt) {
        this.qtt = qtt;
    }
}

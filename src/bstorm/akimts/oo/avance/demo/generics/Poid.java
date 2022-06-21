package bstorm.akimts.oo.avance.demo.generics;

public class Poid implements Mesure {

    private double poid;
    private String unite;

    public Poid(double poid, String unite) {
        this.poid = poid;
        this.unite = unite;
    }

    public double getPoid() {
        return poid;
    }

    public void setPoid(double poid) {
        this.poid = poid;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
}

package bstorm.akimts.oo.avance.exo;

public enum Localisation {

    REGIONAL(1_000, 25), // ordinal : 0
    NATIONAL(10_000, 50), // ordinal : 1
    INTERNATIONAL(100_000, 100); // ordinal : 2

    private final int gain;
    private final int limitePart;

    Localisation(int gain, int limitePart) {
        this.gain = gain;
        this.limitePart = limitePart;
    }

    public int getGain() {
        return gain;
    }

    public int getLimitePart() {
        return limitePart;
    }

    public Localisation meilleure(Localisation aComparer){
        return this.ordinal() > aComparer.ordinal() ? this : aComparer;
    }

}

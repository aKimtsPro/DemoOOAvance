package bstorm.akimts.oo.avance.demo;

public enum TypeInstrument {

    VENT(80),
    PERCUSSION(60),
    CORDE(100);

    private final int popularite;

    TypeInstrument(int popularite) {
        this.popularite = popularite;
    }

    public int getPopularite() {
        return popularite;
    }


    public int calculerPrix(){
        return 4*popularite;
    }
}

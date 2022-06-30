package bstorm.akimts.oo.avance.exo.threads;

public class Carcasse {

    private TypeCarcasse type;

    public Carcasse(TypeCarcasse type) {
        this.type = type;
    }

    public TypeCarcasse getType() {
        return type;
    }

    public void setType(TypeCarcasse type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Carcasse{" +
                "type='" + type + '\'' +
                '}';
    }
}

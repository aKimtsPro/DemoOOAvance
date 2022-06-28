package bstorm.akimts.oo.avance.demo.streams;

public class Personne implements Comparable<Personne>{

    private String nom;
    private int age;

    public Personne(String nom, int age) {
        System.out.println("J'instancie une personne");
        this.nom = nom;
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        // Pour egal obj doit être une Personne non null doit le nom et l'age doivent être les memes que this
        if(obj == null) return false;
        if(obj instanceof Personne)
            return ((Personne)obj).getNom().equals(this.getNom()) && ((Personne)obj).getAge() == this.getAge();

        return false;
    }

    @Override
    public int compareTo(Personne o) {
        if( o == null )
            return 1; // si o est null, this est supérieur (valeur positive)
        return this.getAge() - o.getAge(); // this est supérieur si son age est supérieur
    }
}

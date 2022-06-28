package bstorm.akimts.oo.avance.exo.stream;

import java.util.Collection;
import java.util.List;

public class VilleService {

    private Collection<Ville> villes;

    public VilleService() {
        setup();
    }

    public void setup(){
        List<Habitant> hegiature = List.of(
                new Habitant("Moore", "Thierry", "rue Barbe", 86),
                new Habitant("Kimtsaris", "Alexandre", "rue de la ferme", 207),
                new Habitant("Cabuchon", "Melanie", "rue Vazy", 49),
                new Habitant("Colat", "Thierry", "rue Barbe", 86),
                new Habitant("Zaine", "Aude", "boulevard Icelle", 158)
        );

        List<Habitant> brequin = List.of(
                new Habitant("Delpeche", "Maude", "rue Stique", 128),
                new Habitant("Otor", "Amine", "avenue Tritif", 64),
                new Habitant("Delince", "Jean", "rue  Tilante", 7),
                new Habitant("Tovarovitch", "Mélodie", "rue Bonhomme", 2),
                new Habitant("Richard", "Nathan", "avenue Truc", 64),
                new Habitant("Rapier", "William", "rue Machin", 128)
        );

        List<Habitant> quithyme = List.of(
                new Habitant("Harrison", "Paul", "rue Barbatif", 207),
                new Habitant("Cament", "Medhi", "rue du Passage", 200),
                new Habitant("Richard", "Nathan", "avenue Truc", 64)
        );

        this.villes = List.of(
                new Ville(1000, "Hégiature", hegiature, 1500),
                new Ville(2510, "Brequin", brequin, 800),
                new Ville(9510, "Quityme", quithyme, 3000)
        );
    }
}

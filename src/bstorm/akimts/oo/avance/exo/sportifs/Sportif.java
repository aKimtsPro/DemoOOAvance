package bstorm.akimts.oo.avance.exo.sportifs;

import java.time.LocalDate;
import java.util.Random;

public abstract class Sportif {

    private String nom;
    private String prenom;
    private LocalDate dateNaiss;

    public Sportif(String nom, String prenom, LocalDate dateNaiss) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
    }

    public abstract int performer();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }
}

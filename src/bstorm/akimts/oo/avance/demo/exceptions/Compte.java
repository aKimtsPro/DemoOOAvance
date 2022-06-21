package bstorm.akimts.oo.avance.demo.exceptions;

import bstorm.akimts.oo.avance.demo.exceptions.MontantNegatifException;
import bstorm.akimts.oo.avance.demo.exceptions.SoldeInsuffisantException;

public class Compte {

    private String nomProp;
    private double solde = 100;


    public void retrait(double montant){
        if(montant < 0)
            throw new MontantNegatifException();

        if(montant <= solde)
            solde -= montant;
        else
            throw new SoldeInsuffisantException(solde, montant);
    }

    /**
     * permet de faire un depot sur le compte
     * @param montant, doit être positif
     */
    public void depot(double montant){
        if(montant < 0)
            throw new MontantNegatifException();

        solde += montant;
    }



    public String getNomProp() {
        return nomProp;
    }

    public void setNomProp(String nomProp) {
        this.nomProp = nomProp;
    }

    public double getSolde() {
        return solde;
    }

}

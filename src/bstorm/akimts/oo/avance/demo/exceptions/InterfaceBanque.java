package bstorm.akimts.oo.avance.demo.exceptions;

import java.io.*;
import java.util.Scanner;

public class InterfaceBanque {

    private Scanner sc = new Scanner(System.in);
    private Compte compte = new Compte();


    public void start(){
        boolean continuer = true;
        while(continuer){

            System.out.println("votre solde est de :" + compte.getSolde());
            double retrait = sc.nextDouble();

            try{
                compte.retrait(retrait);
            }
            catch (MontantNegatifException ex){
                System.out.println(ex.getMessage());
            }
            catch (SoldeInsuffisantException ex){
                System.out.println("solde insuffisant");
                System.out.println("solde restant: " + ex.getSoldeRestant());
                System.out.println("retrait: " + ex.getRetrait());
            }


            System.out.println("continuer?");
            continuer = sc.next().charAt(0) != 'n';
        }

        try {
            saveInfo();
        }
        catch (IOException ex){
            System.out.println("sauvegarde impossible: fichier invalide");
        }

    }

    private void saveInfo() throws IOException {
        PrintWriter pw = new PrintWriter("sa_\\_i*fo.t/t", "UTF-8");
    }




}

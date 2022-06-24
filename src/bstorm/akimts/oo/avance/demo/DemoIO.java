package bstorm.akimts.oo.avance.demo;

import bstorm.akimts.oo.avance.demo.fichier.Personne;
import bstorm.akimts.oo.avance.demo.fichier.PersonneService;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DemoIO {


    public static void main(String[] args) {

        // Demander à l'utilisateur son nom et sa taille et l'ecrire dans un fichier
        Scanner sc = new Scanner(System.in);


        try (  // Les resources ne seront disponible que dans le try puis seront .close() automatiquement
                FileWriter fw = new FileWriter("resources/new_file.txt");
//                FileWriter fw1 = new FileWriter("resources/new_file.txt");
        ) {
            // Par défaut, append à faux
            String nom = sc.nextLine();
            int age = sc.nextInt();
            fw.write( String.format("%s %d\n", nom, age) );

        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        catch (InputMismatchException ex){
            System.out.println("valeurs invalides");
        }
        // Proche du code généré par try with resources
//        finally {
//
//            if(fw != null){
//                try {
//                    fw.close();
//                }
//                catch (IOException ex){
//                    ex.printStackTrace();
//                }
//            }
//
//        }

        PersonneService service = new PersonneService("outFile.csv");
//        service.ecrire( new Personne("lucas", "dubosquet", LocalDate.now()));

        try {
            List<Personne> list = service.lire();

            for (Personne personne : list) {
                System.out.println( personne );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package bstorm.akimts.oo.avance.demo.fichier;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonneService {

    private File inOut;

    public PersonneService(String path) {
        this.inOut = new File(path);
    }

    // format .csv Commat Separated Values
    public void ecrire(Personne aEcrire){

        if(aEcrire == null)
            throw new IllegalArgumentException("arg should not be null");

        try ( PrintStream ps = new PrintStream(new FileOutputStream(inOut, true)) ) {
            ps.printf("\n%s,%s,%3$td-%3$tm-%3$tY",aEcrire.getNom(), aEcrire.getPrenom(), aEcrire.getDateNaissance());
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public List<Personne> lire() throws Exception{

        List<Personne> list = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(inOut)) ) {

            br.readLine();
            String line = null;
            while((line = br.readLine()) != null ){

                Personne p = convertir(line);
                list.add(p);

            }
            return list;

        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        throw new Exception("un problème a eut lieu lors de la lecture du fichier");

    }

    private Personne convertir(String aConvertir) throws Exception{

        String[] data = aConvertir.split(",");

        if( data.length != 3 )
            throw new Exception("erreur de conversion");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // 22-06-2022
        LocalDate date = LocalDate.parse(data[2], formatter);
        return new Personne(data[0], data[1], date);

    }

}

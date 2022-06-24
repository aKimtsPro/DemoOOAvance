package bstorm.akimts.oo.avance.exo;

import bstorm.akimts.oo.avance.demo.annotation.NonCompetitif;
import bstorm.akimts.oo.avance.exo.exceptions.EtatCompetitionException;
import bstorm.akimts.oo.avance.exo.exceptions.LimiteAtteinteException;
import bstorm.akimts.oo.avance.exo.sportifs.Sportif;

import java.util.*;

public class CompetitionImpl<T extends Sportif> implements Competition<T> {

    private final Map<T, Integer> participants = new HashMap<>();
    private List<T> classements;
    private final Localisation localisation;

    public CompetitionImpl(Localisation localisation) {
        this.localisation = localisation;
    }


    @Override
    public void lancer() {

        // n'est pas terminé
        if( isTerminee()  )
            throw new EtatCompetitionException(isTerminee() , false);

        // Pas de participants
        if( participants.size() <= 3 )
            throw new IllegalStateException("La compet n'a pas de participants");

        for (T sportif : participants.keySet()) {
            participants.put(sportif, sportif.performer());
        }

        classements = genererClassement();
        Set<T> gagnants = getGagnants();
        int argentReparti = localisation.getGain() / gagnants.size();

        for (T gagnant : gagnants) {
            gagnant.ajouterGain( argentReparti );
        }
    }

    /**
     * Inscrit un sportif à la compétition.
     * La compétition ne peut être terminée.
     * La limite de participant ne peut être dépassée.
     * On ne peut pas inscrire 2x la même
     *
     * @param aInscrire
     */
    @Override
    public void inscrire(T aInscrire) {

        // n'est pas termine
        if( isTerminee()  )
            throw new EtatCompetitionException(isTerminee() , false);

        // il y a de la place
        if( localisation.getLimitePart() != 0 && participants.size() >= localisation.getLimitePart() )
            throw new LimiteAtteinteException( localisation.getLimitePart() );

        // doit etre non inscrit+
        if( participants.containsKey(aInscrire) )
            throw new IllegalArgumentException("T déjà inscrit");

        NonCompetitif annotation = aInscrire.getClass().getAnnotation(NonCompetitif.class);
        if( annotation != null ){
            Localisation[] localisations = annotation.value();
            for (Localisation localisation1 : localisations) {
                if( localisation1 == localisation )
                    throw new IllegalArgumentException("Le sportif est non compétitif");
            }
        }


        participants.put(aInscrire, null);
    }

    @Override
    public void desinscrire(T T) {

        // deja terminée
        if( isTerminee()  )
            throw new EtatCompetitionException(isTerminee() , false);

        // n'existe pas
        if( !participants.containsKey(T) )
            throw new IllegalArgumentException("T non inscrit");

        participants.remove(T);
    }

    @Override
    public Set<T> getGagnants() {

        if( !isTerminee() )
            throw new EtatCompetitionException(isTerminee() , true);

        Collection<Integer> values = participants.values();
        int maxPerf = 0;
        for (Integer value : values) {
            if( value > maxPerf )
                maxPerf = value;
        }

        Set<T> gagnants = new HashSet<>();
        for (Map.Entry<T, Integer> entry : participants.entrySet()) {
            if( entry.getValue() == maxPerf )
                gagnants.add(entry.getKey() );
        }

//        int maxPerf = participants.values()
//                .stream()
//                .mapToInt(i -> i)
//                .max()
//                .getAsInt();

//        Set<T> gagnants = participants.entrySet().stream()
//                .filter( e -> e.getValue() == maxPerf )
//                .map( Map.Entry::getKey )
//                .collect(Collectors.toSet());

        return gagnants;
    }

    @Override
    public boolean isTerminee() {
        return classements != null;
    }

    @Override
    public int getLimiteParticipant() {
        return localisation.getLimitePart();
    }

    public Localisation getLocalisation(){
        return localisation;
    }

    private List<T> genererClassement(){

        List<T> classement = new ArrayList<>();
        Set<T> set = participants.keySet();
        for (T T : set) {

            boolean place = false;
            for (int i = 0;!place && i < classement.size() ; i++) {

                T currentT = classement.get(i);
                int currentPerf = participants.get(currentT);

                int perfSportAPlacer = participants.get(T);

                if( perfSportAPlacer > currentPerf ){
                    classement.add(i, T);
                    place = true;
                }
            }

            if( !place )
                classement.add(T);

        }

        return classement;
    }

    public List<T> getClassements() {
        return new ArrayList<>(classements);
    }

    public void inscrire(Collection<? extends T> aInscrire){
        for (T sportif : aInscrire) {
            inscrire(sportif);
        }
    }

    public void transfertParticipants(CompetitionImpl<? super T> autreCompet){
        autreCompet.inscrire( participants.keySet() );
    }

    // Créer une compétition non terminée sur base d'une autre.
    // Elle aura les mêmes participants
    public static <Type extends Sportif> CompetitionImpl<Type> fusionner(CompetitionImpl<? extends Type> membre1, CompetitionImpl<? extends Type> membre2){

        CompetitionImpl<Type> compet = new CompetitionImpl<>( membre1.localisation.meilleure(membre2.localisation) );
        membre1.transfertParticipants(compet);
        membre2.transfertParticipants(compet);
        return compet;

    }

    public <O extends T> Set<O> getOfType( Class<O> clazz ){
        Set<O> set = new HashSet<>();

        for (T part : participants.keySet()) {
            if(part != null && part.getClass().equals(clazz))
                set.add((O)part);
        }

        return set;
    }
}



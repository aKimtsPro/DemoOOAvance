package bstorm.akimts.oo.avance.exo.lambdas;

import bstorm.akimts.oo.avance.exo.CompetitionImpl;
import bstorm.akimts.oo.avance.exo.sportifs.Sportif;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

public class ExoLambda {

    static int i;

    public static void main(String[] args) throws InterruptedException {


        Consumer<Integer> l2 = newTemp -> System.out.println("il fait maintenant " +newTemp+ "°. C'est plus froid");

                //newTemp -> System.out.println("il fait maintenant " +newTemp+ "°");
        Thermometer thermometer = new Thermometer(20);
        thermometer.subTempUp( newTemp -> System.out.println("il fait maintenant " +newTemp+ "°. C'est plus chaud") );
        thermometer.setTemperature(21);//
        Consumer<Integer> l3 = newTemp -> System.out.println("y fai cho");
        thermometer.subTempUp( l3 );
        thermometer.setTemperature(25);//
        thermometer.setTemperature(22);//
        thermometer.subTempDown(l2);
        thermometer.setTemperature(0);//
        thermometer.setTemperature(-5);//




    }

}

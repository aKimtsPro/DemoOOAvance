package bstorm.akimts.oo.avance.exo.stream;

import java.util.List;

public class ExoStream {

    public static void main(String[] args) {

        VilleService service = new VilleService();
        service.taxe('H');

        System.out.println( service.getMostTaxed().getTotalTaxes() );

    }

}

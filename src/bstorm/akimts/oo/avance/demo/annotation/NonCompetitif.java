package bstorm.akimts.oo.avance.demo.annotation;

import bstorm.akimts.oo.avance.exo.Localisation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( {ElementType.TYPE} )
@Retention( RetentionPolicy.RUNTIME )
public @interface NonCompetitif {
    Localisation[] value();
//    Localisation[] localisation();
}

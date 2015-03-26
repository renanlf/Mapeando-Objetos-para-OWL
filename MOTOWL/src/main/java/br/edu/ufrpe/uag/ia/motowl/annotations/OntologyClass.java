package br.edu.ufrpe.uag.ia.motowl.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// atributo de classe
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OntologyClass {
    String value();
}

/**
 * 
 */
package br.edu.ufrpe.uag.ia.motowl.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Renan
 * @since 27/03/2015
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjectProperty {
    String value() default "";
}

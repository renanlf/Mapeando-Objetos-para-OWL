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
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SubClassOf {
    /**
     * Esta variável indica o value atribuido na anotação OntologyClass da classe em que esta classe é subclasse.
     * Exemplo: Se a classe A é subclasse de B então a anotação ficará @SubClassOf("A").
     * @return retorna o nome da classe pai da classe anotada.
     */
    String value();
}

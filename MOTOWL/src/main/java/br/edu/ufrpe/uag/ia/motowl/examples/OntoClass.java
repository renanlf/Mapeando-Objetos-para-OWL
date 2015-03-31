/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufrpe.uag.ia.motowl.examples;

import br.edu.ufrpe.uag.ia.motowl.annotations.ObjectProperty;
import br.edu.ufrpe.uag.ia.motowl.annotations.OntologyClass;

/**
 *
 * @author Renan
 * @since Expression datetime is undefined on line 13, column 13 in
 * Templates/Classes/Class.java.
 */
@OntologyClass
class OntoClass {

    @ObjectProperty
    private Idade hasIdade;

    public OntoClass(int idade) {
        this.hasIdade = new Idade(idade);
    }

}

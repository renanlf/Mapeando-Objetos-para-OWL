/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufrpe.uag.ia.motowl.examples;

import br.edu.ufrpe.uag.ia.motowl.annotations.ObjectProperty;
import br.edu.ufrpe.uag.ia.motowl.annotations.OntologyClass;
import br.edu.ufrpe.uag.ia.motowl.annotations.SubClassOf;

/**
 *
 * @author Renan
 * @since Expression datetime is undefined on line 13, column 13 in
 * Templates/Classes/Class.java.
 */
@OntologyClass("OntoFilho")
@SubClassOf("OntoClass")
class Filho {

    @ObjectProperty
    private Idade hasIdade;

    public Filho(int idade) {
        this.hasIdade = new Idade(idade);
    }

}

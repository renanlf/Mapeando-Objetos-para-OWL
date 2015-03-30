/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ufrpe.uag.ia.motowl.factories;

import br.edu.ufrpe.ia.motowl.managers.MappingObjectManager;
import java.util.HashSet;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

/**
 *
 * @author Renan
 * @since Expression datetime is undefined on line 13, column 13 in Templates/Classes/Class.java.
 */
public class MappingObjectManagerFactory {
    private static MappingObjectManagerFactory instance;

    /**
     * @return the instance
     */
    public static MappingObjectManagerFactory getInstance() {
        if(instance == null){
            instance = new MappingObjectManagerFactory();
        }
        return instance;
    }
    
    public MappingObjectManager createObjectManager(String stringIRI, String path, HashSet<String> classNames) throws OWLOntologyCreationException{
        return new MappingObjectManager(this, stringIRI, path, classNames);
    }
    
    
}

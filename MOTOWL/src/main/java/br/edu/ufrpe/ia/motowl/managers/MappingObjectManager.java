/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ufrpe.ia.motowl.managers;

import br.edu.ufrpe.uag.ia.motowl.annotations.OntologyClass;
import br.edu.ufrpe.uag.ia.motowl.factories.MappingObjectManagerFactory;
import java.util.HashSet;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

/**
 *
 * @author Renan
 * Esta classe é um administrador do mapeamento. É a partir daqui que são mapeados os objetos para a ontologia.
 */
public class MappingObjectManager {
    private String path;
    private final MappingObjectManagerFactory factory;
    private final MappedOntology mappedOntology;
    
    private HashSet<String> classNames;
    
    public MappingObjectManager(MappingObjectManagerFactory factory, String stringIRI, String path,
            HashSet<String> classNames) throws OWLOntologyCreationException{
        this.factory = factory;
        this.path = path;
        this.classNames = classNames;
        this.mappedOntology = new MappedOntology(stringIRI, path);
    }
    /**
     * Este método pega o nome das classes inseridos no xml e cria seus representantes na ontologia.
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException 
     */
    public void doMappingForClasses() throws NoSuchMethodException, ClassNotFoundException{
        for(String className : classNames){
            OntologyClass ontoClass = (OntologyClass) Class.forName(className).getDeclaredAnnotation(OntologyClass.class);
            if(ontoClass != null){
                throw new NoSuchMethodException("Ainda não implementado");
            }
        }
    }
}

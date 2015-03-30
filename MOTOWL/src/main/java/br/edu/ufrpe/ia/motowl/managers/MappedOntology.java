/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ufrpe.ia.motowl.managers;

import java.util.HashSet;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyManagerFactory;

/**
 *
 * @author Renan
 * @since Expression datetime is undefined on line 13, column 13 in Templates/Classes/Class.java.
 */
public class MappedOntology {
    private final HashSet<OWLClass> classes;
    private final HashSet<OWLObjectProperty> objectProperties;
    private final HashSet<OWLDataProperty> dataProperties;
    
    private final OWLOntologyManager manager;
    private final OWLDataFactory dataFactory;
    private final OWLOntology ontology;
    
    private String path;
    
    public MappedOntology(String stringIRI, String path) throws OWLOntologyCreationException{
        this.classes = new HashSet<OWLClass>();
        this.objectProperties = new HashSet<OWLObjectProperty>();
        this.dataProperties = new HashSet<OWLDataProperty>();
        this.manager = OWLManager.createOWLOntologyManager();
        this.dataFactory = manager.getOWLDataFactory();
        IRI iri = IRI.create(stringIRI);
        this.ontology = manager.createOntology(iri);
    }
    
    
}

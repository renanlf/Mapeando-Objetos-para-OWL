package br.edu.ufrpe.uag.ia.motowl.annotations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import junit.framework.Assert;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLParserFactoryRegistry;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyManagerFactory;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLOntologyStorerFactoryRegistry;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.vocab.PrefixOWLOntologyFormat;

public class OntologyClassTest {
    
    @Test
    public void example() throws OWLOntologyCreationException, OWLOntologyStorageException, FileNotFoundException{
	//criação de uma classe utilizando a anotação
	@OntologyClass("OntoClass")
	class OntoClass{
            @ObjectProperty
	    private Byte objectProperty;
            @DataProperty
            private int dataProperty;
	}
	
	//instancia da classe anotada
	OntoClass o = new OntoClass();
	//instanciando a anotação
        OntologyClass a = (OntologyClass) o.getClass().getDeclaredAnnotation(OntologyClass.class);
        if(a != null){
            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
            OWLDataFactory dataFactory = manager.getOWLDataFactory();
            IRI ontologyIRI = IRI.create("http://example.com/owlapi/ontology");
            OWLOntology ontology = manager.createOntology(ontologyIRI);
            PrefixManager prefixManager = new DefaultPrefixManager(ontologyIRI+"#");
            
            OWLClass owlClass = dataFactory.getOWLClass(":"+a.value(), prefixManager);
            
            OWLDeclarationAxiom declarationAxiom = dataFactory.getOWLDeclarationAxiom(owlClass);
            
            manager.addAxiom(ontology, declarationAxiom);
            
            manager.saveOntology(ontology, new FileOutputStream("t.owl"));
            
            
            
        }
        
	
	
	
    }
}

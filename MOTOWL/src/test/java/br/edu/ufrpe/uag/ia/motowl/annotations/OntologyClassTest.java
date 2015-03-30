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
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
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
import uk.ac.manchester.cs.owl.owlapi.OWLNaryDataRangeImpl;

public class OntologyClassTest {

    @Test
    public void example() throws OWLOntologyCreationException, OWLOntologyStorageException, FileNotFoundException {
        //criação de uma classe utilizando a anotação
        @OntologyClass("OntoClass")
        class OntoClass {

            @ObjectProperty
            private Byte objectProperty;
            @DataProperty
            private int dataProperty;

        }

        //instancia da classe anotada
        OntoClass o = new OntoClass();
        //instanciando a anotação
        OntologyClass a = (OntologyClass) o.getClass().getDeclaredAnnotation(OntologyClass.class);

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLDataFactory dataFactory = manager.getOWLDataFactory();
        IRI ontologyIRI = IRI.create("http://example.com/owlapi/ontology");
        OWLOntology ontology = manager.createOntology(ontologyIRI);
        PrefixManager prefixManager = new DefaultPrefixManager(ontologyIRI + "#");
        if (a != null) {

            OWLClass owlClass = dataFactory.getOWLClass(":" + a.value(), prefixManager);

            OWLDeclarationAxiom declarationAxiom = dataFactory.getOWLDeclarationAxiom(owlClass);

            manager.addAxiom(ontology, declarationAxiom);
        }
        
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("Domain: " + o.getClass().getSimpleName());
                OWLClass domainClass = dataFactory.getOWLClass(":" + o.getClass().getSimpleName(), prefixManager);
                if (annotation instanceof ObjectProperty) {
                    System.out.println("Range: " + field.getType().getSimpleName());
                    OWLClass rangeClass = dataFactory.getOWLClass(":" + field.getType().getSimpleName(), prefixManager);

                    OWLObjectProperty objectProperty = dataFactory.getOWLObjectProperty(":" + field.getName(), prefixManager);
                    System.out.println("Object Property: " + field.getName());

                    OWLObjectPropertyDomainAxiom domainAxiom = dataFactory.getOWLObjectPropertyDomainAxiom(objectProperty, domainClass);
                    OWLObjectPropertyRangeAxiom rangeAxiom = dataFactory.getOWLObjectPropertyRangeAxiom(objectProperty, rangeClass);

                    AddAxiom addAxiom1 = new AddAxiom(ontology, domainAxiom);
                    AddAxiom addAxiom2 = new AddAxiom(ontology, rangeAxiom);

                    manager.applyChange(addAxiom1);
                    manager.applyChange(addAxiom2);
                } else {
                    if (annotation instanceof DataProperty) {
                        OWLDataProperty dataProperty = dataFactory.getOWLDataProperty(":" + field.getName(), prefixManager);

                        OWLDataPropertyDomainAxiom domainAxiom = dataFactory.getOWLDataPropertyDomainAxiom(dataProperty, domainClass);
                        OWLDataPropertyRangeAxiom rangeAxiom = dataFactory.getOWLDataPropertyRangeAxiom(dataProperty, null);
                        
                        AddAxiom addAxiom1 = new AddAxiom(ontology, domainAxiom);
			AddAxiom addAxiom2 = new AddAxiom(ontology, rangeAxiom);
			
			manager.applyChange(addAxiom1);
			manager.applyChange(addAxiom2);

                        System.out.println("Data Property: " + field.getName());
                    }
                }
            }
            OWLOntologyFormat fmt = new OWLXMLOntologyFormat();
            manager.saveOntology(ontology, new FileOutputStream("C:/Users/Renan/Desktop/t.owl"));
        }

    }

}

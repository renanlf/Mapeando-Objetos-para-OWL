package br.edu.ufrpe.uag.ia.motowl.annotations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashSet;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

public class OntologyClassTest {

    @Test
    public void example() throws OWLOntologyCreationException,
	    OWLOntologyStorageException, ClassNotFoundException, IOException {

	// instancias dos objetos utilizados para gerar o owl
	OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	IRI ontologyIRI = IRI.create("http://example.com/owlapi/ontology");
	OWLOntology ontology = manager.createOntology(ontologyIRI);
	PrefixManager prefixManager = new DefaultPrefixManager(ontologyIRI
		+ "#");
	OWLDataFactory dataFactory = manager.getOWLDataFactory();

	// instancia do hashset com os nomes das classes mapeadas
	// As classes no XML devem ser colocadas neste HashSet
	HashSet<String> classNames = new HashSet<String>();
	classNames.add("br.edu.ufrpe.uag.ia.motowl.examples.OntoClass");
	classNames.add("br.edu.ufrpe.uag.ia.motowl.examples.Filho");
	classNames.add("br.edu.ufrpe.uag.ia.motowl.examples.Idade");

	// criando as OWLClass baseado no HashSet que terá os nomes das classes
	// no XMLs
	for (String className : classNames) {
	    // anotação para mapear a classe para a classe da owl
	    OntologyClass annotationClass = (OntologyClass) Class.forName(
		    className).getDeclaredAnnotation(OntologyClass.class);
	    if (annotationClass != null) {
		// pega o nome que o usuario deseja utilizar
		String nameOWLClass = annotationClass.value().length() == 0 ? Class
			.forName(className).getSimpleName() : annotationClass
			.value();
		OWLClass owlClass = dataFactory.getOWLClass(":" + nameOWLClass,
			prefixManager);

		OWLDeclarationAxiom declarationAxiom = dataFactory
			.getOWLDeclarationAxiom(owlClass);
		System.out.println(owlClass);
		manager.addAxiom(ontology, declarationAxiom);
	    }
	}
	// axiomas de subclassof
	for (String className : classNames) {
	    // anotação para a subclasse
	    SubClassOf annotationSub = (SubClassOf) Class.forName(className)
		    .getDeclaredAnnotation(SubClassOf.class);

	    OntologyClass annotationClass = (OntologyClass) Class.forName(
		    className).getDeclaredAnnotation(OntologyClass.class);

	    if (annotationSub != null && annotationClass != null) {
		// instancia de objetos owl
		OWLClass superClass = dataFactory.getOWLClass(":"
			+ annotationSub.value(), prefixManager);

		String nameOWLClass = annotationClass.value().length() == 0 ? Class
			.forName(className).getSimpleName() : annotationClass
			.value();
		OWLClass subClass = dataFactory.getOWLClass(":" + nameOWLClass,
			prefixManager);

		OWLSubClassOfAxiom subAxiom = dataFactory
			.getOWLSubClassOfAxiom(subClass, superClass);
		System.out.println(subAxiom);
		// adiciona axioma a ontologia
		manager.addAxiom(ontology, subAxiom);
	    }
	}
	// axiomas de objectProperty
	for (String className : classNames) {
	    Field[] fields = Class.forName(className).getDeclaredFields();
	    OntologyClass annotationClass = (OntologyClass) Class.forName(
		    className).getDeclaredAnnotation(OntologyClass.class);
	    String nameDomain = annotationClass.value().length() == 0 ? Class
		    .forName(className).getSimpleName() : annotationClass
		    .value();
	    // ler todos os campos da classe
	    for (Field field : fields) {
		ObjectProperty annotationObj = (ObjectProperty) field
			.getDeclaredAnnotation(ObjectProperty.class);
		if (annotationObj != null) {
		    // se o field possui annotation de ObjectProperty então pode
		    // continuar
		    OntologyClass annotationClassRange = (OntologyClass) Class
			    .forName(field.getType().getName())
			    .getDeclaredAnnotation(OntologyClass.class);
		    String nameRange = annotationClassRange.value().length() == 0 ? Class
			    .forName(field.getType().getName()).getSimpleName()
			    : annotationClassRange.value();

		    String objectPropertyName = annotationObj.value().length() == 0 ? field
			    .getName() : annotationObj.value();
		    // instancias OWL
		    OWLClass domain = dataFactory.getOWLClass(":" + nameDomain,
			    prefixManager);
		    OWLClass range = dataFactory.getOWLClass(":" + nameRange,
			    prefixManager);

		    OWLObjectProperty objectProperty = dataFactory
			    .getOWLObjectProperty(":" + objectPropertyName,
				    prefixManager);
		    // instancia dos axiomas
		    OWLObjectPropertyDomainAxiom domainAxiom = dataFactory
			    .getOWLObjectPropertyDomainAxiom(objectProperty,
				    domain);
		    OWLObjectPropertyRangeAxiom rangeAxiom = dataFactory
			    .getOWLObjectPropertyRangeAxiom(objectProperty,
				    range);

		    // adiciona axiomas a ontologia
		    AddAxiom addAxiom1 = new AddAxiom(ontology, domainAxiom);
		    AddAxiom addAxiom2 = new AddAxiom(ontology, rangeAxiom);
		    System.out.println(domainAxiom + "\n" + rangeAxiom);
		    manager.applyChange(addAxiom1);
		    manager.applyChange(addAxiom2);
		}

	    }
	}
	// axiomas de dataProperty
	for (String className : classNames) {
	    Field[] fields = Class.forName(className).getDeclaredFields();
	    OntologyClass annotationClass = (OntologyClass) Class.forName(
		    className).getDeclaredAnnotation(OntologyClass.class);
	    String nameDomain = annotationClass.value().length() == 0 ? Class
		    .forName(className).getSimpleName() : annotationClass
		    .value();
	    for (Field field : fields) {
		DataProperty annotationData = (DataProperty) field
			.getDeclaredAnnotation(DataProperty.class);
		if (annotationData != null) {

		    String dataPropertyName = annotationData.value().length() == 0 ? field
			    .getName() : annotationData.value();

		    OWLDataRange range = null;
		    String nameRange = field.getType().getSimpleName();
		    if (nameRange.equals("Integer") || nameRange.equals("int")) {
			range = dataFactory.getIntegerOWLDatatype();
		    } else if (nameRange.equals("Double")
			    || nameRange.equals("double")) {
			range = dataFactory.getDoubleOWLDatatype();
		    } else if (nameRange.equals("Boolean")
			    || nameRange.equals("boolean")) {
			range = dataFactory.getBooleanOWLDatatype();
		    }
		    OWLClass domain = dataFactory.getOWLClass(":" + nameDomain,
			    prefixManager);

		    OWLDataProperty dataProperty = dataFactory
			    .getOWLDataProperty(":" + dataPropertyName,
				    prefixManager);

		    OWLDataPropertyDomainAxiom domainAxiom = dataFactory
			    .getOWLDataPropertyDomainAxiom(dataProperty, domain);
		    OWLDataPropertyRangeAxiom rangeAxiom = dataFactory
			    .getOWLDataPropertyRangeAxiom(dataProperty, range);

		    AddAxiom addAxiom1 = new AddAxiom(ontology, domainAxiom);
		    AddAxiom addAxiom2 = new AddAxiom(ontology, rangeAxiom);
		    System.out.println(domainAxiom + "\n" + rangeAxiom);
		    manager.applyChange(addAxiom1);
		    manager.applyChange(addAxiom2);
		}

	    }
	}
	
	// OUTPUT DE SAIDA DA ONTOLOGIA, ONDE SERA ESCRITA
		OutputStream stringOutputStream = new FileOutputStream(new File("ontology.owl"));
		// SALVA A ONTOLOGIA EM UM OUTPUT PARA STRING
		manager.saveOntology(ontology, stringOutputStream);
		
		
    }

}

package br.edu.ufrpe.uag.ia.motowl.annotations;

import java.lang.annotation.Annotation;

import junit.framework.Assert;

import org.junit.Test;

public class OntologyClassTest {
    
    @Test
    public void example(){
	//criação de uma classe utilizando a anotação
	@OntologyClass("OntoClass")
	class OntoClass{
	    
	}
	
	//instancia da classe anotada
	OntoClass o = new OntoClass();
	//instanciando a anotação
	Annotation a = o.getClass().getAnnotation(OntologyClass.class);
	//fazendo o cast
	OntologyClass myAnnotation = (OntologyClass) a;
	//fazendo o teste
	Assert.assertEquals("OntoClass", myAnnotation.value());
	
	
	
    }
}

package br.edu.ufrpe.uag.ia.motowl.interfaces;
import java.io.Serializable;
import java.util.Set;

/**
 * 
 */

/**
 * @author israel
 *
 */
public interface InterfaceClassLoader extends Serializable {

	/**
	 * Loads a class from an InterfaceNodeIdentifier
	 * 
	 * @param node
	 * @return
	 * @throws ClassNotFoundException
	 */
	Class<?> loadClass(InterfaceNodeIdentifier node)
			throws ClassNotFoundException;

	/**
	 * Loads all Classes from a xml Document
	 * 
	 * @param xml
	 * @return
	 * @throws ClassNotFoundException
	 */
	Set<Class<?>> loadClasses(InterfaceXMLParser xml)
			throws ClassNotFoundException;

}

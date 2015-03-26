package br.edu.ufrpe.uag.ia.motowl.interfaces;
import java.io.Serializable;

/**
 * 
 */

/**
 * @author israel
 *
 */
public interface InterfaceNodeIdentifier extends Serializable {

	/**
	 * Gets a head
	 * 
	 * @return
	 */
	String getHead();

	/**
	 * Sets a head
	 * 
	 * @param toSet
	 */
	void setHead(String toSet);

	/**
	 * Gets the body
	 * 
	 * @return
	 */
	String getBody();

	/**
	 * Sets the bogy
	 * 
	 * @param toBody
	 */
	void setBody(String toBody);

}

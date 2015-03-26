package br.edu.ufrpe.uag.ia.motowl.interfaces;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.InvalidPropertiesFormatException;
import java.util.Set;

/**
 * 
 */

/**
 * @author israel
 *
 */
public interface InterfaceXMLParser extends Serializable {

	/**
	 * Inserts an InterfaceNodeIdentifier in a xml document
	 * 
	 * @param node
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void addIdentifier(InterfaceNodeIdentifier node)
			throws FileNotFoundException, IOException;

	/**
	 * Gets an InterfaceNodeIdentifier in a xml document using a head
	 * 
	 * @param head
	 * @return
	 */
	InterfaceNodeIdentifier getIdentifier(String head);

	/**
	 * Lists all InterfaceNodeIdentifier from a xml document
	 * 
	 * @return
	 */
	Set<InterfaceNodeIdentifier> listIdetifiers();

	/**
	 * Gets de outputStream
	 * 
	 * @return
	 * @throws FileNotFoundException 
	 */

	FileOutputStream getFileOutputStream() throws FileNotFoundException;

	/**
	 * Gets de inputStream
	 * 
	 * @return
	 * @throws FileNotFoundException 
	 */
	FileInputStream getFileInputStream() throws FileNotFoundException;

	/**
	 * Loads from an inputStream
	 * 
	 * @throws InvalidPropertiesFormatException
	 * @throws IOException
	 */
	void load() throws InvalidPropertiesFormatException, IOException;

	/**
	 * Save to an outputStream
	 * 
	 * @throws IOException
	 */
	void save() throws IOException;

}

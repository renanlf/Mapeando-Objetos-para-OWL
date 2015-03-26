package br.edu.ufrpe.uag.ia.motowl.abstracts;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import br.edu.ufrpe.uag.ia.motowl.interfaces.InterfaceNodeIdentifier;
import br.edu.ufrpe.uag.ia.motowl.interfaces.InterfaceXMLParser;
import br.edu.ufrpe.uag.ia.motowl.xml.Node;

/**
 * 
 */

/**
 * @author israel
 *
 */
public abstract class AbstractXMLParser implements InterfaceXMLParser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Properties xmls = new Properties();

	@Override
	public void addIdentifier(InterfaceNodeIdentifier node)
			throws FileNotFoundException, IOException {
		xmls.put(node.getHead(), node.getBody());
		save();
	}

	@Override
	public InterfaceNodeIdentifier getIdentifier(String head) {
		return new Node(head, xmls.getProperty(head));
	}

	@Override
	public Set<InterfaceNodeIdentifier> listIdetifiers() {
		HashSet<InterfaceNodeIdentifier> toReturn = new HashSet<>();
		for (Entry<Object, Object> toAdd : xmls.entrySet()) {
			toReturn.add(new Node(toAdd.getKey().toString(), toAdd.getValue()
					.toString()));
		}
		return toReturn;
	}

	@Override
	public void load() throws InvalidPropertiesFormatException, IOException {
		xmls.loadFromXML(getFileInputStream());
	}

	@Override
	public void save() throws IOException {
		xmls.storeToXML(getFileOutputStream(), "Mapped OWL classes");
	}
}

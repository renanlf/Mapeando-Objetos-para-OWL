package br.edu.ufrpe.uag.ia.motowl.loader;

import java.util.HashSet;
import java.util.Set;

import br.edu.ufrpe.uag.ia.motowl.interfaces.InterfaceClassLoader;
import br.edu.ufrpe.uag.ia.motowl.interfaces.InterfaceNodeIdentifier;
import br.edu.ufrpe.uag.ia.motowl.interfaces.InterfaceXMLParser;

/**
 * 
 */

/**
 * @author israel
 *
 */
public class ClassLoader implements InterfaceClassLoader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ClassLoader() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> loadClass(InterfaceNodeIdentifier node)
			throws ClassNotFoundException {
		return Class.forName(node.getBody());
	}

	@Override
	public Set<Class<?>> loadClasses(InterfaceXMLParser xml)
			throws ClassNotFoundException {
		HashSet<Class<?>> toReturn = new HashSet<>();
		for (InterfaceNodeIdentifier node : xml.listIdetifiers()) {
			toReturn.add(loadClass(node));
		}
		return toReturn;
	}

}

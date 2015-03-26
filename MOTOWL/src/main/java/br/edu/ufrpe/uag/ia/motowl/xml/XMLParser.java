package br.edu.ufrpe.uag.ia.motowl.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import br.edu.ufrpe.uag.ia.motowl.abstracts.AbstractXMLParser;

/**
 * 
 */

/**
 * @author israel
 *
 */
public class XMLParser extends AbstractXMLParser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File xml;

	/**
	 * @throws IOException
	 * @throws InvalidPropertiesFormatException
	 * 
	 */
	public XMLParser(String fileName) throws InvalidPropertiesFormatException,
			IOException {
		// TODO Auto-generated constructor stub
		this.xml = new File(fileName);
		System.out.println(this.xml);
		if (xml.length() != 0) {
			load();
		}
	}

	@Override
	public FileOutputStream getFileOutputStream() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return new FileOutputStream(xml, false);
	}

	@Override
	public FileInputStream getFileInputStream() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return new FileInputStream(xml);
	}

}

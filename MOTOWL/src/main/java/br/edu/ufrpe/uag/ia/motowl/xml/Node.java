package br.edu.ufrpe.uag.ia.motowl.xml;

import br.edu.ufrpe.uag.ia.motowl.interfaces.InterfaceNodeIdentifier;
/**
 * 
 */

/**
 * @author israel
 *
 */
public class Node implements InterfaceNodeIdentifier {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String head;
	private String body;

	/**
	 * 
	 */
	public Node() {
		// TODO Auto-generated constructor stub
	}

	public Node(String head, String body) {
		this.head = head;
		this.body = body;
	}

	
	@Override
	public String getHead() {
		// TODO Auto-generated method stub
		return this.head;
	}

	
	@Override
	public void setHead(String toSet) {
		this.head = toSet;

	}

	
	@Override
	public String getBody() {
		return this.body;
	}

	
	@Override
	public void setBody(String toBody) {
		this.body = toBody;

	}

}

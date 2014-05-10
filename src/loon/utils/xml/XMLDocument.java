package loon.utils.xml;


public class XMLDocument {

	private String header;

	private XMLElement root;

	public XMLDocument(XMLElement e) {
		this("<?xml version=\"1.0\" standalone=\"yes\" ?>\n", e);
	}

	public XMLElement getRoot() {
		return this.root;
	}

	public String getHeader() {
		return this.header;
	}

	@Override
	public String toString() {
		return this.header + this.root.toString();
	}

	public XMLDocument(String header, XMLElement root) {
		this.header = header;
		this.root = root;
	}

}

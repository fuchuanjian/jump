package loon.utils.xml;


public class XMLData {
	
	private String text;

	@Override
	public String toString() {
		return this.text;
	}

	public XMLData(String paramString) {
		this.text = paramString;
	}
}

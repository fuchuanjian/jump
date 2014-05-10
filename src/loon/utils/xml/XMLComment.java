package loon.utils.xml;


public class XMLComment {
	
	private String text;

	public String getText() {
		return this.text;
	}

	@Override
	public String toString() {
		return "<!--" + this.text + "-->";
	}

	public XMLComment(String paramString) {
		this.text = paramString;
	}
}

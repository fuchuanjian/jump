package loon.utils.xml;


public class XMLProcessing {
	
	private String text;

	public XMLProcessing(String paramString) {
		this.text = paramString;
	}

	public String getText() {
		return this.text;
	}

	@Override
	public String toString() {
		return "<?" + this.text + "?>";
	}

}

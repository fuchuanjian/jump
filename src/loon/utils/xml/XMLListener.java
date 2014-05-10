package loon.utils.xml;


public interface XMLListener {

	public void addHeader(int line, XMLProcessing xp);

	public void addComment(int line, XMLComment c);

	public void addData(int line, XMLData data);

	public void addAttribute(int line, XMLAttribute a);

	public void addElement(int line, XMLElement e);
	
	public void endElement(int line, XMLElement e);
}

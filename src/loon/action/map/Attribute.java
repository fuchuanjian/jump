package loon.action.map;


public class Attribute {
	
	private String name;

	private Object attribute;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getAttribute() {
		return this.attribute;
	}

	public int getAttributeInt() {
		return ((Integer) this.attribute).intValue();
	}

	public void setAttribute(Object attribute) {
		this.attribute = attribute;
	}
	
}

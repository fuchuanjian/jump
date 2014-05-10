package loon.utils.xml;



public class XMLAttribute {

	private String name;

	private String value;

	XMLElement element;

	XMLAttribute(String n, String v) {
		this.name = n;
		this.value = v;
	}
	
	public XMLElement getElement() {
		return element;
	}

	public String getValue() {
		return this.value;
	}

	public int getIntValue() {
		try {
			return Integer.parseInt(this.value);
		} catch (Exception ex) {
			throw new RuntimeException("Attribute '" + this.name
					+ "' has value '" + this.value
					+ "' which is not an integer !");
		}
	}

	public float getFloatValue() {
		try {
			return Float.parseFloat(this.value);
		} catch (Exception ex) {
			throw new RuntimeException("Attribute '" + this.name
					+ "' has value '" + this.value
					+ "' which is not an float !");
		}
	}

	public double getDoubleValue() {
		try {
			return Double.parseDouble(this.value);
		} catch (Exception ex) {
			throw new RuntimeException("Attribute '" + this.name
					+ "' has value '" + this.value
					+ "' which is not an double !");
		}
	}

	public boolean getBoolValue() {
		if (value == null) {
			return false;
		}
		return "true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value);
	}

	public String getName() {
		return this.name;
	}

}

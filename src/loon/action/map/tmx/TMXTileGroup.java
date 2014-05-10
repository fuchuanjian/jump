package loon.action.map.tmx;

import java.util.ArrayList;

import loon.utils.xml.XMLElement;



public class TMXTileGroup {

	public int index;

	public String name;

	public ArrayList<TMXTile> objects;

	public int width;

	public int height;

	public TMXProperty props;

	public TMXTileGroup(XMLElement element) throws RuntimeException {
		name = element.getAttribute("name", null);
		width = element.getIntAttribute("width", 0);
		height = element.getIntAttribute("height", 0);
		objects = new ArrayList<TMXTile>();

		XMLElement propsElement = element.getChildrenByName("properties");
		if (propsElement != null) {
			ArrayList<XMLElement> properties = propsElement.list("property");
			if (properties != null) {
				props = new TMXProperty();
				for (int p = 0; p < properties.size(); p++) {
					XMLElement propElement = properties.get(p);
					String name = propElement.getAttribute("name", null);
					String value = propElement.getAttribute("value", null);
					props.setProperty(name, value);
				}
			}
		}
		ArrayList<XMLElement> objectNodes = element.list("object");
		for (int i = 0; i < objectNodes.size(); i++) {
			XMLElement objElement = objectNodes.get(i);
			TMXTile object = new TMXTile(objElement);
			object.index = i;
			objects.add(object);
		}
	}

}

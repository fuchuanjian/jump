
package loon.action.sprite.node;

import java.util.ArrayList;

import loon.core.geom.Vector2f;
import loon.utils.StringUtils;

public class DefinitionObject {

	private ArrayList<String> elementNames;

	public DefinitionObject parentDefinitionObject = null;

	public String fileName;

	public void childDefinitionObject(DefinitionObject childObject, String str) {
	}

	public void childDefinitionObjectDidFinishParsing(
			DefinitionObject childObject) {
	}

	public void childDefinitionObjectDidInit(DefinitionObject childObject) {
	}

	public void definitionObjectDidFinishParsing() {
	}

	public void definitionObjectDidInit() {
		this.elementNames = new ArrayList<String>();
	}

	public void definitionObjectDidReceiveString(String value) {
	}

	public DefinitionObject initWithParentObject(DefinitionObject parentObject) {
		this.parentDefinitionObject = parentObject;
		return this;
	}

	public static Vector2f strToVector2(String str) {
		String[] result = StringUtils.split(str, ",");
		String name = result[0];
		String value = result[1];
		return new Vector2f(Float.parseFloat(name), Float.parseFloat(value));
	}

	public void undefinedElementDidFinish(String elementName) {
		String result = this.elementNames.get(this.elementNames.size() - 1);
		if (result.equals(elementName)) {
			this.elementNames.remove(result);
		}
	}

	public void undefinedElementDidReceiveString(String str) {
	}

	public void undefinedElementDidStart(String elementName) {
		this.elementNames.add(elementName);
	}

	private boolean goto_flag = false;

	protected ArrayList<String> getResult(String v) {
		StringBuilder buffer = new StringBuilder();
		ArrayList<String> result = new ArrayList<String>(20);
		char[] chars = v.toCharArray();
		int size = chars.length;
		for (int i = 0; i < size; i++) {
			char ch = chars[i];
			if (ch == '/') {
				goto_flag = true;
			} else if ((ch == '\n' | ch == ';')) {
				String mess = buffer.toString();
				int len = mess.length();
				if (len > 0) {
					result.add(mess);
					buffer.delete(0, len);
				}
				goto_flag = false;
			} else if (!goto_flag && ch != 0x9) {
				buffer.append(ch);
			}
		}
		if (buffer.length() > 0) {
			result.add(buffer.toString());
		}
		buffer = null;
		return result;
	}
}

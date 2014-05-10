package loon.action.map.tmx;

import java.util.Hashtable;


public class TMXProperty extends Hashtable<String, String> {

	
	private static final long serialVersionUID = 1L;

	public synchronized Object setProperty(String key, String value) {
		return put(key, value);
	}

	public String getProperty(String key, String defaultValue) {
		String val = getProperty(key);
		return (val == null) ? defaultValue : val;
	}

	public String getProperty(String key) {
		Object oval = super.get(key);
		String sval = (oval instanceof String) ? (String) oval : null;
		return sval;
	}

}

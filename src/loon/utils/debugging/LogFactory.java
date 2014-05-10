package loon.utils.debugging;

import java.util.WeakHashMap;

import loon.core.LSystem;


public class LogFactory {

	final static private WeakHashMap<String, Object> lazyMap = new WeakHashMap<String, Object>(
			LSystem.DEFAULT_MAX_CACHE_SIZE);

	public static Log getInstance(String app) {
		return getInstance(app, 0);
	}

	public static Log getInstance(String app, int type) {
		String key = app.toLowerCase();
		Object obj = lazyMap.get(key);
		if (obj == null) {
			lazyMap.put(key, obj = new Log(app, type));
		}
		return (Log) obj;
	}

	public static Log getInstance(Class<?> clazz) {
		String key = clazz.getName().toLowerCase();
		Object obj = lazyMap.get(key);
		if (obj == null) {
			lazyMap.put(key, obj = new Log(clazz));
		}
		return (Log) obj;
	}

	public static void clear() {
		lazyMap.clear();
	}

}

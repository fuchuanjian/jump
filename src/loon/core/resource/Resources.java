package loon.core.resource;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import loon.core.LSystem;
import loon.utils.StringUtils;
import loon.utils.collection.ArrayByte;

import android.content.res.AssetManager;


public abstract class Resources {

	// 以下为0.3.2版中新增资源读取方法(为避免冲突，同时保留了原始读取方式)
	
	public static Resource classRes(String path) {
		return new ClassRes(path);
	}

	
	public static Resource fileRes(String path) {
		return new FileRes(path);
	}

	
	public static Resource remoteRes(String path) {
		return new RemoteRes(path);
	}

	
	public static Resource sdRes(String path) {
		return new SDRes(path);
	}

	
	public final static InputStream strRes(final String path) {
		if (path == null) {
			return null;
		}
		InputStream in = null;
		if (path.indexOf("->") == -1) {
			if (path.startsWith("sd:")) {
				in = sdRes(path.substring(3, path.length())).getInputStream();
			} else if (path.startsWith("class:")) {
				in = classRes(path.substring(6, path.length()))
						.getInputStream();
			} else if (path.startsWith("path:")) {
				in = fileRes(path.substring(5, path.length())).getInputStream();
			} else if (path.startsWith("url:")) {
				in = remoteRes(path.substring(4, path.length()))
						.getInputStream();
			}
		} else {
			String[] newPath = StringUtils.split(path, "->");
			in = new ByteArrayInputStream(LPKResource.openResource(
					newPath[0].trim(), newPath[1].trim()));
		}
		return in;
	}

	private static ClassLoader classLoader;

	private final static Object lock = new Object();

	private final static Map<String, Object> lazyResources = new HashMap<String, Object>(
			LSystem.DEFAULT_MAX_CACHE_SIZE);

	static {
		try {
			// 在Android中Thread.currentThread()方式等于被废|||……
			// classLoader = Thread.currentThread().getContextClassLoader();
			classLoader = Resources.class.getClassLoader();
		} catch (Throwable ex) {
			classLoader = null;
		}
	}

	
	public static Iterator<String> getNames() {
		synchronized (lock) {
			return lazyResources.keySet().iterator();
		}
	}

	
	public static boolean contains(String resName) {
		synchronized (lock) {
			return (lazyResources.get(resName) != null);
		}
	}

	
	public static void remove(String resName) {
		synchronized (lock) {
			lazyResources.remove(resName);
		}
	}

	public static void destroy() {
		lazyResources.clear();
	}

	@Override
	public void finalize() {
		destroy();
	}

	
	public final static ClassLoader getClassLoader() {
		return classLoader;
	}

	
	public final static ClassLoader getClassLoader(Class<?> clazz) {
		return clazz.getClassLoader();
	}

	
	public final static android.content.res.Resources getResources() {
		return LSystem.screenActivity.getResources();
	}

	
	public static InputStream openResource(String resName, final ClassLoader c)
			throws IOException {
		if (resName.indexOf("\\") != -1) {
			resName = resName.replace("\\", "/");
		}
		final InputStream result = c.getResourceAsStream(resName);
		if (result == null) {
			throw new IOException("Exception to load resource [" + resName
					+ "] .");
		}
		return result;
	}

	private final static String assetsFlag = "assets";

	
	public static InputStream openResource(String resName) throws IOException {
		InputStream resource = strRes(resName);
		if (resource != null) {
			return resource;
		}
		if (resName.indexOf("\\") != -1) {
			resName = resName.replace("\\", "/");
		}
		String fileName = resName.toLowerCase();
		if (fileName.startsWith(assetsFlag)
				|| fileName.startsWith(LSystem.FS + assetsFlag)) {
			boolean flag = resName.startsWith(LSystem.FS);
			AssetManager asset = LSystem.screenActivity.getAssets();
			String file;
			if (flag) {
				file = resName.substring(1);
			} else {
				file = resName;
			}
			int index = file.indexOf(LSystem.FS) + 1;
			if (index != -1) {
				file = resName.substring(index);
			} else {
				int length = file.length();
				int size = file.lastIndexOf(LSystem.FS, 0) + 1;
				if (size < length) {
					file = file.substring(size, length);
				}
			}
			return asset.open(file);
		}
		if (classLoader != null) {
			InputStream in = null;
			try {
				in = classLoader.getResourceAsStream(resName);
			} catch (Exception e) {
				try {
					in = LSystem.getResourceAsStream(resName);
				} catch (Exception ex) {
					throw new RuntimeException(resName + " not found!");
				}
			}
			if (in == null) {
				in = LSystem.getResourceAsStream(resName);
			}
			return in;
		} else {
			return LSystem.screenActivity.getAssets().open(resName);
		}
	}

	
	public final static ArrayByte getResource(String resName) {
		if (resName == null) {
			return null;
		}
		if (resName.indexOf("\\") != -1) {
			resName = resName.replace("\\", "/");
		}
		InputStream resource = strRes(resName);
		if (resource != null) {
			ArrayByte result = new ArrayByte();
			try {
				result.write(resource);
				resource.close();
				result.reset();
			} catch (IOException ex) {
				result = null;
			}
			return result;
		}
		resName = resName.startsWith(LSystem.FS) ? resName.substring(1)
				: resName;
		String innerName = resName;
		String keyName = innerName.replaceAll(" ", "").toLowerCase();
		synchronized (lock) {
			if (lazyResources.size() > LSystem.DEFAULT_MAX_CACHE_SIZE) {
				lazyResources.clear();
			}
			byte[] data = (byte[]) lazyResources.get(keyName);
			if (data != null) {
				return new ArrayByte(data);
			}
		}
		InputStream in = null;
		// 外部文件标志
		boolean filePath = StringUtils.startsWith(innerName, '$');
		if (filePath) {
			try {
				innerName = innerName.substring(1, innerName.length());
				in = new BufferedInputStream(new FileInputStream(new File(
						innerName)));
			} catch (Exception ex) {
				if (in != null) {
					LSystem.close(in);
				}
				throw new RuntimeException(resName + " file not found !");
			}
		} else {
			try {
				in = openResource(innerName);
			} catch (IOException e) {
				throw new RuntimeException(resName + " file not found !");
			}
		}
		ArrayByte byteArray = new ArrayByte();
		try {
			byteArray.write(in);
			in.close();
			byteArray.reset();
			lazyResources.put(keyName, byteArray.getData());
		} catch (IOException ex) {
			byteArray = null;
		}
		if (byteArray == null) {
			throw new RuntimeException(resName + " file not found !");
		}
		return byteArray;
	}

	
	public static InputStream getResourceAsStream(final String fileName) {
		if ((fileName.indexOf("file:") >= 0) || (fileName.indexOf(":/") > 0)) {
			try {
				URL url = new URL(fileName);
				return new BufferedInputStream(url.openStream());
			} catch (Exception e) {
				return null;
			}
		}
		return new ByteArrayInputStream(getResource(fileName).getData());
	}

	
	final static public byte[] getDataSource(InputStream is) {
		if (is == null) {
			return null;
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] bytes = new byte[8192];
		try {
			int read;
			while ((read = is.read(bytes)) >= 0) {
				byteArrayOutputStream.write(bytes, 0, read);
			}
			bytes = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			return null;
		} finally {
			try {
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.flush();
					byteArrayOutputStream = null;
				}
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
			}
		}
		return bytes;
	}

	public final static InputStream getResource(Class<?> clazz, String resName) {
		return clazz.getResourceAsStream(resName);
	}

}

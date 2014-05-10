package loon.core.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import loon.core.LSystem;
import loon.utils.StringUtils;


// 在JavaSE版中没有SDCard，操作模式与FileRes一致，而在Android版中则为SDCard操作(如果未安装SD卡，则尝试读取本地缓存中文件)
public class SDRes extends DataRes implements Resource {

	public SDRes(String path) {
		if (isMoutedSD()) {
			File f = android.os.Environment.getExternalStorageDirectory();
			String tmp = f.getPath();
			if (StringUtils.startsWith(path, '/')) {
				path = path.substring(1);
			}
			if (!StringUtils.endsWith(tmp, '/')) {
				path = tmp + "/" + path;
			} else {
				path = tmp + path;
			}
		} else {
			path = LSystem.screenActivity.getCacheDir().getAbsolutePath();
			path = StringUtils.replaceIgnoreCase(path, "\\", "/");
			if (StringUtils.startsWith(path, '/') || StringUtils.startsWith(path, '\\')) {
				path = path.substring(1, path.length());
			}
		}
		this.path = path;
		this.name = "sdcard://" + path;
	}

	public final static boolean isMoutedSD() {
		String sdState = android.os.Environment.getExternalStorageState();
		return sdState.equals(android.os.Environment.MEDIA_MOUNTED);
	}

	@Override
	public InputStream getInputStream() {
		try {
			if (in != null) {
				return in;
			}
			return (in = new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("file " + name + " not found !", e);
		}
	}

	@Override
	public String getResourceName() {
		return name;
	}

	@Override
	public URI getURI() {
		try {
			if (uri != null) {
				return uri;
			}
			return (uri = new URL(path).toURI());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SDRes other = (SDRes) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}

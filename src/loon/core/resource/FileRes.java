package loon.core.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;


public class FileRes extends DataRes implements Resource {

	public FileRes(String path) {
		this.path = path;
		this.name = "file://" + path;
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
		FileRes other = (FileRes) obj;
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
	public int hashCode(){
		return super.hashCode();
	}
}

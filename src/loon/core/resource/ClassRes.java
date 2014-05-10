package loon.core.resource;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import loon.core.LSystem;



public class ClassRes extends DataRes implements Resource {

	private ClassLoader classLoader;

	public ClassRes(String path) {
		this(path, null);
	}

	public ClassRes(String path, ClassLoader classLoader) {
		this.path = path;
		this.name = "classpath://" + path;
		this.classLoader = classLoader;
	}

	@Override
	public InputStream getInputStream() {
		try {
			if (in != null) {
				return in;
			}
			if (classLoader == null) {
				return (in = LSystem.getResourceAsStream(path));
			} else {
				return (in = classLoader.getResourceAsStream(path));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
			return (uri = classLoader.getResource(path).toURI());
		} catch (URISyntaxException ex) {
			throw new RuntimeException(ex);
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
		ClassRes other = (ClassRes) obj;
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

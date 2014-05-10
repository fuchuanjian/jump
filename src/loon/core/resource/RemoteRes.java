package loon.core.resource;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;


public class RemoteRes extends DataRes implements Resource {

	public RemoteRes(String url) {
		this.path = url;
		this.name = url;
	}

	@Override
	public InputStream getInputStream() {
		try {
			if (in != null) {
				return in;
			}
			return in = new URL(path).openStream();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getResourceName() {
		return name;
	}

	@Override
	public URI getURI() {
		try {
			return new URL(path).toURI();
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
		RemoteRes other = (RemoteRes) obj;
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

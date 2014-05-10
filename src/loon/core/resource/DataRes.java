package loon.core.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;


abstract class DataRes {

	String path;

	String name;

	InputStream in;

	URI uri;

	@Override
	public int hashCode() {
		return (name == null) ? super.hashCode() : name.hashCode();
	}

	public void dispose() {
		if (in != null) {
			try {
				in.close();
				in = null;
			} catch (IOException e) {
			}
		}
		if (uri != null) {
			uri = null;
		}
	}
}

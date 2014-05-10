package loon.core.resource;

import java.io.InputStream;
import java.net.URI;

import loon.core.LRelease;



public interface Resource extends LRelease{

	InputStream getInputStream();

	String getResourceName();

	URI getURI();
}

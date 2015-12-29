package de.rnd7.urlcache;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

class StreamHandler extends URLStreamHandler {
	private final String fileName;

	StreamHandler(final String fileName) {
		this.fileName = fileName;
	}

	@Override
	protected URLConnection openConnection(final URL u) throws IOException {
		final URL resource = StreamHandler.class.getResource(this.fileName);
		return resource.openConnection();
	}
}
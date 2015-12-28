package de.rnd7.urlcache;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

class StreamHandlerFactory implements URLStreamHandlerFactory {

	private final URLStreamHandler streamHandler;

	StreamHandlerFactory(final URLStreamHandler streamHandler) {
		this.streamHandler = streamHandler;
	}

	@Override
	public URLStreamHandler createURLStreamHandler(final String protocol) {
		return this.streamHandler;
	}
}
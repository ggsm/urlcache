package de.rnd7.urlcache;

import java.net.URL;

import org.apache.commons.codec.digest.DigestUtils;

public class URLCacheKey {
	private final URL url;
	private final String key;

	public URLCacheKey(final URL url, final String key) {
		this.url = url;
		this.key = key;
	}

	public URL getUrl() {
		return this.url;
	}

	public String getKey() {
		return this.key;
	}

	public static URLCacheKey of(final URL url) {
		final String key = DigestUtils.sha1Hex(url.toExternalForm());
		return new URLCacheKey(url, key);
	}
}

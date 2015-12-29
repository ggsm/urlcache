package de.rnd7.urlcache;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class URLCacheLoaderTest {
	@Rule
	public TemporaryFolder cacheFolder = new TemporaryFolder();

	@Test
	public void testCacheMiss() throws Exception {
		final URLCacheLoader loader = new URLCacheLoader(this.cacheFolder.newFolder());
		final CachedElement element = loader.load(this.mockKey("foo"));

		Assert.assertEquals("foo", IOUtils.toString(element.openStream()));
	}

	@Test
	public void testCachedContentChanged() throws Exception {
		final URLCacheLoader loader = new URLCacheLoader(this.cacheFolder.newFolder());
		loader.load(this.mockKey("foo"));
		final CachedElement element = loader.load(this.mockKey("bar"));

		Assert.assertEquals("foo", IOUtils.toString(element.openStream()));
	}

	private URLCacheKey mockKey(final String content) throws IOException {
		final URL url = new URL("http", "rnd7.de", 80, "", new StreamHandler("foo"));
		return new URLCacheKey(url, content);
	}
}

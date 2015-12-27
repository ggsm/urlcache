package de.rnd7.urlcache;

import java.io.File;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

public final class URLCacheFactory {
	private URLCacheFactory() {
	}

	public static LoadingCache<URLCacheKey, CachedElement> create(final File localCacheFolder) {
		return CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.MINUTES)
				.build(new URLCacheLoader(localCacheFolder));
	}
}

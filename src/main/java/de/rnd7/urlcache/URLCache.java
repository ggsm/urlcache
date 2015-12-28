package de.rnd7.urlcache;

import java.util.concurrent.ExecutionException;

public interface URLCache {
	CachedElement get(URLCacheKey key) throws ExecutionException;
}

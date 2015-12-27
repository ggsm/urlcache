# urlcache

The intention of this guava cache implementation is the usage for libtvdb only.
The terms of usage for the API of http://thetvdb.com requires cached access to the zip data. This is implemented here.

## Example usage
```
		final File cacheFolder = new File("/local/folder");
		cacheFolder.mkdirs();

		final LoadingCache<URLCacheKey, CachedElement> cache = URLCacheFactory.create(cacheFolder);

		final URL url = new URL("http://www.github.com/");
		final CachedElement element = cache.get(URLCacheKey.of(url));
```

/**
 * Copyright 2015 Philipp Arndt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

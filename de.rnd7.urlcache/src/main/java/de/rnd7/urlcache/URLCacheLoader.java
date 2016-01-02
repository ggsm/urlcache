/**
 * Copyright 2016 Philipp Arndt
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import org.apache.commons.io.IOUtils;

import com.google.common.cache.CacheLoader;

final class URLCacheLoader extends CacheLoader<URLCacheKey, CachedElement> {

	private final File cacheFolder;

	public URLCacheLoader(final File cacheFolder) {
		this.cacheFolder = cacheFolder;
	}

	@Override
	public CachedElement load(final URLCacheKey key) throws Exception {
		final Optional<CachedElement> fromDisk = this.loadFromDisk(key);
		if (fromDisk.isPresent()) {
			return fromDisk.get();
		}

		final CachedElement element = this.loadFromURL(key);
		this.saveToDisk(element, key);

		return element;
	}

	private void saveToDisk(final CachedElement element, final URLCacheKey key) throws IOException {
		final File localFile = this.toLocalFile(key);

		try (FileOutputStream fos = new FileOutputStream(localFile);
				ObjectOutputStream out = new ObjectOutputStream(fos);) {

			out.writeObject(element);
			out.flush();
		}
	}

	private CachedElement loadFromURL(final URLCacheKey key) throws IOException {
		final CachedElement element = new CachedElement();
		element.setBytes(IOUtils.toByteArray(key.getUrl()));
		element.setValidUntil(CachedElement.createTimeoutDate());

		return element;
	}

	private File toLocalFile(final URLCacheKey key) {
		return new File(this.cacheFolder, key.getKey());
	}

	private Optional<CachedElement> loadFromDisk(final URLCacheKey key) throws IOException {
		final File localFile = this.toLocalFile(key);

		Optional<CachedElement> result = Optional.empty();

		if (localFile.exists()) {
			try (final InputStream in = new FileInputStream(localFile);
					final ObjectInputStream objIn = new ObjectInputStream(in);) {

				final CachedElement element = (CachedElement) objIn.readObject();
				result = Optional.of(element);
			} catch (final ClassNotFoundException e) {
				throw new IOException(e);
			}
		}

		if (result.isPresent()) {
			if (!result.get().isValid()) {
				localFile.delete();
				result = Optional.empty();
			}
		}

		return result;
	}

}

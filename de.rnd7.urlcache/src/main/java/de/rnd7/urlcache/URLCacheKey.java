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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.key == null) ? 0 : this.key.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final URLCacheKey other = (URLCacheKey) obj;
		if (this.key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!this.key.equals(other.key)) {
			return false;
		}
		return true;
	}

}

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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class CachedElement implements Serializable {

	private static final long serialVersionUID = 1788562074753497540L;

	private LocalDate validUntil;

	private byte[] bytes;

	public void setBytes(final byte[] bytes) {
		this.bytes = new byte[bytes.length];
		System.arraycopy(bytes, 0, this.bytes, 0, bytes.length);
	}

	public void setValidUntil(final LocalDate validUntil) {
		this.validUntil = validUntil;
	}

	public boolean isValid() {
		return (this.validUntil == null) || LocalDate.now().isBefore(this.validUntil);
	}

	public InputStream openStream() {
		return new ByteArrayInputStream(this.bytes);
	}

	public static LocalDate createTimeoutDate() {
		final LocalDate time = LocalDate.now();
		return time.plus(1, ChronoUnit.WEEKS);
	}

}

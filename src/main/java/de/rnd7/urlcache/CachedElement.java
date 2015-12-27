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

	public InputStream openInputStream() {
		return new ByteArrayInputStream(this.bytes);
	}

	public static LocalDate createTimeoutDate() {
		final LocalDate time = LocalDate.now();
		return time.plus(1, ChronoUnit.WEEKS);
	}

}

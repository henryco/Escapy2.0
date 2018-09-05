package net.tindersamurai.escapy.components.model.plain.util;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicBoolean;

public final class UpWrapper<T> {

	private final T content;
	private AtomicBoolean updated;

	public UpWrapper(T content) {
		this.updated = new AtomicBoolean(false);
		this.content = content;
	}

	public void setUpdated(boolean val) {
		updated.set(val);
	}

	public boolean isUpdated() {
		return updated.get();
	}

	public T get() {
		return content;
	}
}
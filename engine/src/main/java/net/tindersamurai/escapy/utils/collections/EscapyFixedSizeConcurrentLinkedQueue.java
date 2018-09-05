package net.tindersamurai.escapy.utils.collections;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EscapyFixedSizeConcurrentLinkedQueue<T> extends ConcurrentLinkedQueue<T> {

	private final int size;

	public EscapyFixedSizeConcurrentLinkedQueue(int size) {
		this.size = size;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean a = super.addAll(c);
		while (size() >= size) remove();
		return a;
	}

	@Override
	public boolean add(T o) {
		while (size() >= size) remove();
		return super.add(o);
	}
}
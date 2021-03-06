package net.tindersamurai.escapy.utils.collections.array;


import net.tindersamurai.escapy.utils.EscapyNamed;

import java.util.Collection;
import java.util.Comparator;

/**
 * @author Henry on 11/07/17.
 */
public interface EscapyAssociatedArray <T> extends Iterable<T> {

	void addAll(Collection<String> names, Collection<T> objects);

	void add(T ob, String name);

	<U extends EscapyNamed> void add(U ob);

	T get(String name);

	void set(String name, T obj);

	void remove(String name);

	void clear();

	int size();

	void removeLast();

	T getLast();

	Collection<Entry<T>> getEntrySet();

	T[] asArray();

	final class Entry<U> {

		private final String name;
		private final U object;

		public Entry(String name, U object) {
			this.name = name;
			this.object = object;
		}

		public U getObject() {
			return object;
		}

		public String getName() {
			return name;
		}
	}

	void sort(Comparator<T> comparator);
}

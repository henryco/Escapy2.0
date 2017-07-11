package net.irregular.escapy.engine.env.utils.arrContainer;


import java.util.Collection;

/**
 * @author Henry on 11/07/17.
 */
public interface EscapyAssociatedArray <T> extends Iterable<T> {

	void addAll(Collection<String> names, Collection<T> objects);

	void add(T ob, String name);

	T get(String name);

	void set(String name, T obj);

	void remove(String name);

	void clear();

	int size();

	void removeLast();

	T getLast();

	Collection<Entry<T>> getEntrySet();


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

}

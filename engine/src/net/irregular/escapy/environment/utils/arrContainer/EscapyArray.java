package net.irregular.escapy.environment.utils.arrContainer;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author Henry on 02/07/17.
 */
public interface EscapyArray<T> extends Iterable <T> {

	void addAll(Collection<T> collection);

	void add(T ob);

	void set(int index, T ob);

	void removeLast();

	void remove(int index);

	Stream<T> stream();

	int size();

	T get(int index);

	void clear();

	default T getLast() {
		final int index = size() - 1;
		if (index >= 0) return get(index);
		return null;
	}

	T[] asArray();

	void sort(Comparator<T> comparator);

}

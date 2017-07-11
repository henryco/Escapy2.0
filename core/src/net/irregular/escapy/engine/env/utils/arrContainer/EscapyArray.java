package net.irregular.escapy.engine.env.utils.arrContainer;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author Henry on 02/07/17.
 */
public interface EscapyArray<T> {

	EscapyArray addAll(Collection<T> collection);

	EscapyArray add(T ob);

	EscapyArray set(int index, T ob);

	EscapyArray removeLast();

	EscapyArray remove(int index);

	EscapyArray forEach(Consumer<T> consumer);

	Stream<T> stream();

	int size();

	T get(int index);

	EscapyArray clear();

	default T getLast() {
		final int index = size() - 1;
		if (index >= 0) return get(index);
		return null;
	}

}

package net.irregular.escapy.engine.env.utils.arrContainer;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author Henry on 02/10/16.
 */
public class EscapyIndexArray<T> implements EscapyArray<T> {

	public T[] container;
	private Class<T> obClass;

	
	public EscapyIndexArray(Class<T> obClass) {
		this(obClass, 0);
	}

	public EscapyIndexArray(Class<T> obClass, int initialCap) {
		this.obClass = obClass;
		this.container = instanceArray(obClass, initialCap);
	}

	public EscapyIndexArray(Class<T> obClass, Collection<T> collection) {
		this.obClass = obClass;
		addAll(collection);
	}

	@Override
	public EscapyArray addAll(Collection<T> collection) {
		this.container = collection.toArray(instanceArray(obClass, 0));
		return this;
	}

	@Override
	public EscapyArray add(T ob) {
		container = addObjToArray(obClass, container, ob);
		return this;
	}

	@Override
	public EscapyArray set(int index, T ob) {
		if (index >= 0 && index < container.length)
			container[index] = ob;
		return this;
	}

	@Override
	public EscapyArray removeLast() {
		container = removeLast(obClass, container);
		return this;
	}

	@Override
	public EscapyArray remove(int index) {
		container = remove(obClass, container, index);
		return this;
	}

	@Override
	public EscapyArray forEach(Consumer<T> consumer) {
		Arrays.stream(container).forEach(consumer);
		return this;
	}

	@Override
	public Stream<T> stream() {
		return Arrays.stream(container);
	}

	@Override
	public int size(){
		return container.length;
	}

	@Override
	public T get(int index) {
		if (container.length > index && index >= 0)
			return container[index];
		return null;
	}


	@Override
	public EscapyArray clear() {
		container = instanceArray(obClass, 0);
		return this;
	}


	@SuppressWarnings("unchecked")
	private static <U> U[] instanceArray(Class<U> obClass, int length) {
		return (U[]) Array.newInstance(obClass, length);
	}

	protected static <U> U[] addObjToArray(Class<U> obClass, U[] superArray, U ob) {
		U[] tmp = instanceArray(obClass, superArray.length + 1);
		System.arraycopy(superArray, 0, tmp, 0, superArray.length);
		tmp[tmp.length - 1] = ob;
		return tmp;
	}

	protected static <U> U[] remove(Class<U> obClass, U[] superArray, int index) {
		if (index >= superArray.length || index < 0) return superArray;
		U[] tmp = instanceArray(obClass, superArray.length - 1);
		int k = 0;
		for (int i = 0; i < superArray.length; i++) {
			if (i != index) {
				tmp[k] = superArray[i];
				k++;
			}
		}
		return tmp;
	}

	protected static <U> U[] removeLast(Class<U> obClass, U[] superArray){
		U[] tmp = instanceArray(obClass, superArray.length - 1);
		System.arraycopy(superArray, 0, tmp, 0, tmp.length);
		return tmp;
	}
}

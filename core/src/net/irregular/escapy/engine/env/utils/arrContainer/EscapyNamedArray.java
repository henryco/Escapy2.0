package net.irregular.escapy.engine.env.utils.arrContainer;

import net.irregular.escapy.engine.env.context.annotation.Dante;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.env.utils.Named;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;


/**
 * @author Henry on 19/10/16.
 */ @EscapyAPI @Dante
public class EscapyNamedArray<T> extends EscapyIndexedArray<T> implements EscapyAssociatedArray<T> {


	private String[] names;
	private String[] namesCopy;

	@EscapyAPI
	public EscapyNamedArray(Class<T> obClass) {
		super(obClass);
		this.names = new String[0];
	}

	@EscapyAPI
	public EscapyNamedArray(Class<T> obClass, int initCap) {
		super(obClass, initCap);
		this.names = new String[initCap];
		this.namesCopy = new String[initCap];
	}

	@EscapyAPI
	public EscapyNamedArray(Class<T> obClass, Collection<String> names, Collection<T> objects) {
		super(obClass);
		addAll(names, objects);
	}


	@Override
	public void addAll(Collection<String> names, Collection<T> objects) {
		addAll(objects);
		this.names = names.toArray(new String[0]);
		copyNames();
	}

	@Override
	public void add(T ob) {
		this.add(ob, Integer.toString(ob.hashCode()));
	}

	@Override
	public void add(T ob, String name) {

		this.names = addObjToArray(String.class, names, name);
		copyNames();
		super.add(ob);
	}



	@Override @SuppressWarnings("unchecked")
	public <U extends Named> void add(U ob) {
		try {
			add((T) ob, ob.getName());
		} catch (Exception ignored) {}
	}


	@EscapyAPI @Override
	public T get(String name) {
		for (int i = 0; i < names.length; i++) if (names[i].equals(name)) return container[i];
		return null;
	}

	@EscapyAPI @Override
	public void set(String name, T obj) {
		for (int i = 0; i < names.length; i++)
			if (names[i].equalsIgnoreCase(name))
				container[i] = obj;
	}

	@EscapyAPI @Override
	public void remove(String name) {
		for (int i = 0; i < names.length; i++)
			if (names[i].equals(name)) {
				remove(i);
				copyNames();
		}
	}

	@EscapyAPI
	public String[] getNames() {
		return namesCopy;
	}

	@Override
	public void clear() {
		this.names = new String[0];
		copyNames();
		super.clear();
	}

	@Override
	public void removeLast() {
		names = removeLast(String.class, names);
		copyNames();
		super.removeLast();
	}

	@Override
	public T getLast() {
		return super.getLast();
	}

	@Override
	public Collection<Entry<T>> getEntrySet() {
		Collection<Entry<T>> collection = new LinkedList<>();
		for (int i = 0; i < size(); i ++) collection.add(new Entry<>(names[i], get(i)));
 		return collection;
	}

	@Override
	public void sort(Comparator<T> comparator) {

		if (comparator == null) return;
		LinkedList<Entry<T>> entries = (LinkedList<Entry<T>>) getEntrySet();
		entries.sort((o1, o2) -> comparator.compare(o1.getObject(), o2.getObject()));
		Collection<T> objects = new LinkedList<>();
		Collection<String> names = new LinkedList<>();
		for (Entry<T> entry: entries) {
			objects.add(entry.getObject());
			names.add(entry.getName());
		}
		addAll(names, objects);
	}


	@Override
	public String toString() {
		return "EscapyNamedArray{" +
				"container=" + Arrays.toString(container) +
				", names=" + Arrays.toString(names) +
				'}';
	}


	private void copyNames(){
		namesCopy = new String[names.length];
		System.arraycopy(names, 0, namesCopy, 0, namesCopy.length);
	}
}

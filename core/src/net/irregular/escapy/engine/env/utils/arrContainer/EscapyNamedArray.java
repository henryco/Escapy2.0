package net.irregular.escapy.engine.env.utils.arrContainer;

import net.irregular.escapy.engine.env.context.annotation.Dante;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;

import java.util.Arrays;
import java.util.Collection;


/**
 * @author Henry on 19/10/16.
 */ @EscapyAPI @Dante
public class EscapyNamedArray<T> extends EscapyIndexArray<T> {


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

	public EscapyNamedArray addAll(Collection<String> names, Collection<T> objects) {
		addAll(objects);
		this.names = names.toArray(new String[0]);
		copyNames();
		return this;
	}

	@Override
	public EscapyArray add(T ob) {
		return this.add(ob, Integer.toString(ob.hashCode()));
	}

	public EscapyArray add(T ob, String name) {

		this.names = addObjToArray(String.class, names, name);
		copyNames();
		return super.add(ob);
	}

	@EscapyAPI
	public T get(String name) {
		for (int i = 0; i < names.length; i++) if (names[i].equals(name)) return container[i];
		return null;
	}

	@EscapyAPI
	public EscapyNamedArray set(String name, T obj) {
		for (int i = 0; i < names.length; i++)
			if (names[i].equalsIgnoreCase(name))
				container[i] = obj;
		return this;
	}

	@EscapyAPI
	public EscapyNamedArray remove(String name) {

		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name)) {
				remove(i);
				copyNames();
			}
		}
		return this;
	}

	@EscapyAPI
	public String[] getNames() {
		return namesCopy;
	}

	@Override
	public EscapyArray clear() {
		this.names = new String[0];
		copyNames();
		return super.clear();
	}

	@Override
	public EscapyArray removeLast() {
		names = removeLast(String.class, names);
		copyNames();
		return super.removeLast();
	}

	private void copyNames(){
		namesCopy = new String[names.length];
		System.arraycopy(names, 0, namesCopy, 0, namesCopy.length);
	}

	@Override
	public String toString() {
		return "EscapyNamedArray{" +
				"container=" + Arrays.toString(container) +
				", names=" + Arrays.toString(names) +
				'}';
	}
}

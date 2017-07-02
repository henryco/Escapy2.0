package net.irregular.escapy.engine.env.utils.arrContainer;

import net.irregular.escapy.engine.env.context.annotation.Dante;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;

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

	@Override
	public EscapyArray add(T ob) {
		return this.addSource(ob, Integer.toString(ob.hashCode()));
	}

	public EscapyArray addSource(T ob, String name) {

		this.names = addObjToArray(String.class, names, name);
		copyNames();
		return super.add(ob);
	}

	@EscapyAPI
	public T get(String name) {
		for (int i = 0; i < names.length; i++) if (names[i].equalsIgnoreCase(name)) return container[i];
		return null;
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
}

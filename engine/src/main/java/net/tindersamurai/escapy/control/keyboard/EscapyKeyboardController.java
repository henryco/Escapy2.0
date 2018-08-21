package net.tindersamurai.escapy.control.keyboard;

import lombok.Getter;
import net.tindersamurai.escapy.control.listener.IEscapyControllerListener;
import net.tindersamurai.escapy.control.IEscapyKeyController;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

public abstract class EscapyKeyboardController<LISTENER extends IEscapyControllerListener>
		implements IEscapyKeyController<Integer, LISTENER> {

	private final Class<LISTENER> listenerClass;
	private final @Getter String name;

	protected @Getter LISTENER[] listeners;
	private Set<LISTENER> tmpSet;

	/* package */ int key;

	/* package */ EscapyKeyboardController(
			String name, Class<LISTENER> listenerClass
	) {
		this.listenerClass = listenerClass;
		this.tmpSet = new HashSet<>();
		this.name = name;
	}

	@Override
	public void setKey(Integer key) {
		this.key = key;
	}

	@Override
	public Integer getKey() {
		return key;
	}

	@Override
	public void addListener(LISTENER listener) {
		if (tmpSet.add(listener))
			listeners = updArray();
	}

	@Override
	public void removeListener(LISTENER listener) {
		if (tmpSet.remove(listener))
			listeners = updArray();
	}

	private LISTENER[] updArray() {
		return tmpSet.toArray(((LISTENER[]) Array.newInstance(listenerClass, 0)));
	}
}
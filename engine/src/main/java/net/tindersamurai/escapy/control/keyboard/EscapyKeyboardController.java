package net.tindersamurai.escapy.control.keyboard;

import lombok.Getter;
import lombok.Setter;
import net.tindersamurai.escapy.control.IEscapyController;
import net.tindersamurai.escapy.control.IEscapyControllerListener;
import net.tindersamurai.escapy.control.IEscapyKeyController;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

public abstract class EscapyKeyboardController<LISTENER extends IEscapyControllerListener>
		implements IEscapyKeyController<Integer, LISTENER> {

	protected @Getter LISTENER[] listeners;
	private @Getter final String name;
	private Set<LISTENER> tmpSet;

	/* package */ int key;

	/* package */ EscapyKeyboardController(String name) {
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
		tmpSet.add(listener);
		listeners = tmpSet.toArray(((LISTENER[]) Array.newInstance(listener.getClass(), 0)));
	}
}
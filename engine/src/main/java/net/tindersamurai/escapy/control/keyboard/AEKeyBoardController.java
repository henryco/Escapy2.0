package net.tindersamurai.escapy.control.keyboard;

import lombok.Getter;
import lombok.Setter;
import net.tindersamurai.escapy.control.IEscapyController;

public abstract class AEKeyBoardController<LISTENER>
		implements IEscapyController<Integer, LISTENER> {

	protected @Setter @Getter LISTENER listener;
	private @Getter final String name;

	/* package */ int key;

	/* package */ AEKeyBoardController(String name) {
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

}
package net.tindersamurai.escapy.controller.keyboard;

import lombok.Getter;
import lombok.Setter;
import net.tindersamurai.escapy.controller.IEscapyController;

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
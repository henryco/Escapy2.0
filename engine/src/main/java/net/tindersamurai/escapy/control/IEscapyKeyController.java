package net.tindersamurai.escapy.control;

import net.tindersamurai.escapy.control.listener.IEscapyControllerListener;

public interface IEscapyKeyController<KEY_TYPE, LISTENER extends IEscapyControllerListener>
		extends IEscapyController<LISTENER> {

	void setKey(KEY_TYPE key);
	KEY_TYPE getKey();

}
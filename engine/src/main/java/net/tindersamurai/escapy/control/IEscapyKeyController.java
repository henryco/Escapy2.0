package net.tindersamurai.escapy.control;

public interface IEscapyKeyController<KEY_TYPE, LISTENER extends IEscapyControllerListener>
		extends IEscapyController<LISTENER> {

	void setKey(KEY_TYPE key);
	KEY_TYPE getKey();

}
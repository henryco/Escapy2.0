package net.tindersamurai.escapy.control;

import net.tindersamurai.escapy.control.IEscapyControllerListener.*;

public interface IEscapyController<KEY_TYPE, LISTENER> {

	String getName();
	void setKey(KEY_TYPE key);
	KEY_TYPE getKey();

	void setListener(LISTENER listener);
	void update();


	interface Interact<T> extends IEscapyController<T, InteractListener> { }
	interface MoveLeft<T> extends IEscapyController<T, MoveLeftListener> { }
	interface MoveRight<T> extends IEscapyController<T, MoveRightListener> { }
	interface Run<T> extends IEscapyController<T, RunListener> { }
	interface Sit<T> extends IEscapyController<T, SitListener> { }

	// TODO MORE

}
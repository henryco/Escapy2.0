package net.tindersamurai.escapy.controller;

public interface IEscapyController<KEY_TYPE, LISTENER> {

	String getName();
	void setKey(KEY_TYPE key);
	KEY_TYPE getKey();

	void setListener(LISTENER listener);
	void update();

	interface Listeners {
		interface InteractListener { void onInteract(); }
		interface MoveLeftListener { void onMoveLeft(); }
		interface MoveRightListener { void onMoveRight(); }
		interface RunListener { void onRun(); }
		interface SitListener { void onSit(); }
	}


	interface Interact<T> extends IEscapyController<T, Listeners.InteractListener> { }
	interface MoveLeft<T> extends IEscapyController<T, Listeners.MoveLeftListener> { }
	interface MoveRight<T> extends IEscapyController<T, Listeners.MoveRightListener> { }
	interface Run<T> extends IEscapyController<T, Listeners.RunListener> { }
	interface Sit<T> extends IEscapyController<T, Listeners.SitListener> { }

	// TODO MORE

}
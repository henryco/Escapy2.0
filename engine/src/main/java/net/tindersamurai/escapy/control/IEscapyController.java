package net.tindersamurai.escapy.control;

import net.tindersamurai.escapy.control.IEscapyControllerListener.*;

public interface IEscapyController<LISTENER extends IEscapyControllerListener> {

	String getName();
	
	void setListener(LISTENER listener);
	void update();


	interface Interact extends IEscapyController<InteractListener> { }
	interface MoveLeft extends IEscapyController<MoveLeftListener> { }
	interface MoveRight extends IEscapyController<MoveRightListener> { }
	interface Run extends IEscapyController<RunListener> { }
	interface Sit extends IEscapyController<SitListener> { }

	// TODO MORE

}
package net.tindersamurai.escapy.components.control.plain;

import net.tindersamurai.escapy.control.listener.IEscapyControllerListener;
import net.tindersamurai.escapy.control.manager.IEscapyControlManager;

public interface AttachableListener extends IEscapyControllerListener {

	default void attach(IEscapyControlManager manager) {
		manager.attachControllerListener(this);
	}

	default void detach(IEscapyControlManager manager) {
		manager.detachControllerListener(this);
	}

	default void attach() {
		this.attach(IEscapyControlManager.instance());
	}

	default void detach() {
		this.detach(IEscapyControlManager.instance());
	}

}
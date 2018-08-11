package net.tindersamurai.escapy.control.manager;

import net.tindersamurai.escapy.control.IEscapyController;
import net.tindersamurai.escapy.control.listener.IEscapyControllerListener;

public interface IEscapyControlManager {
	void registerController(IEscapyController controller);

	void attachControllerListener(IEscapyControllerListener listener);

	void detachControllerListener(IEscapyControllerListener listener);

	void update(float delta);


	static IEscapyControlManager instance() {
		return EscapyControlManager.getInstance();
	}
}

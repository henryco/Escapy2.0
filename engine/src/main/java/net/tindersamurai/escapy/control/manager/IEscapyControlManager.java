package net.tindersamurai.escapy.control.manager;

import net.tindersamurai.escapy.control.IEscapyController;
import net.tindersamurai.escapy.control.listener.IEscapyControllerListener;

public interface IEscapyControlManager {
	void registerController(IEscapyController controller);

	void addControllerListener(IEscapyControllerListener listener);

	void detachControllerListener(IEscapyControllerListener listener);

	void update(float delta);
}

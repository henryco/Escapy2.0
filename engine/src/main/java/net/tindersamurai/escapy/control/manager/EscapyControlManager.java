package net.tindersamurai.escapy.control.manager;

import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.control.IEscapyController;
import net.tindersamurai.escapy.control.listener.IEscapyControllerListener;

import java.util.*;

@Log
public final class EscapyControlManager implements IEscapyControlManager {

	private static final class LazyHolder {
		private static final EscapyControlManager INSTANCE = new EscapyControlManager();
	}

	public static IEscapyControlManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	private EscapyControlManager() {
		this.controllers = new HashMap<>();
		this.listeners = new HashSet<>();
		this.controllersArray = new IEscapyController[0];
		this.listenersArray = new IEscapyControllerListener[0];
	}

	private final Map<String, IEscapyController> controllers;
	private final Set<IEscapyControllerListener> listeners;

	private IEscapyControllerListener[] listenersArray;
	private IEscapyController[] controllersArray;


	@Override
	public void registerController(IEscapyController controller) {
		log.info("REGISTER CONTROLLER: " + controller);
		controllers.put(controller.getName(), controller);
		controllersArray = controllers.values().toArray(new IEscapyController[0]);
	}

	@Override
	public void attachControllerListener(IEscapyControllerListener listener) {
		log.info("ATTACH CONTROLLER LISTENER: " + listener);
		if (listener == null) return;

		listeners.add(listener);
		listenersArray = listeners.toArray(new IEscapyControllerListener[0]);

		for (val c: controllersArray) {
			try {
				//noinspection unchecked
				c.addListener(listener);
			} catch (Exception e) {
				log.warning("Controller listener attach error:" +
						"\n\tListener: " + listener + "" +
						"\n\tController: " + c);
				log.throwing(this.getClass().getName(), "attachControllerListener", e);
				e.printStackTrace();
			}
		}
	}

	@Override
	public void detachControllerListener(IEscapyControllerListener listener) {
		log.info("DETACH CONTROLLER LISTENER: " + listener);
		if (listener == null) return;

		listeners.remove(listener);
		listenersArray = listeners.toArray(new IEscapyControllerListener[0]);

		for (val c: controllersArray) {
			try {
				//noinspection unchecked
				c.removeListener(listener);
			} catch (Exception e) {
				log.warning("Controller listener detach error:" +
						"\n\tListener: " + listener + "" +
						"\n\tController: " + c);
				log.throwing(this.getClass().getName(), "detachControllerListener", e);
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(float delta) {
		for (val l : listenersArray) l.onUpdate(delta);
		for (val c: controllersArray) c.update(delta);
	}
}
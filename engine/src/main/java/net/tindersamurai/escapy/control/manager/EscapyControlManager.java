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
		this.arr = new IEscapyController[0];
	}

	private final Map<String, IEscapyController> controllers;
	private IEscapyController[] arr;


	@Override
	public void registerController(IEscapyController controller) {
		log.info("REGISTER CONTROLLER: " + controller);
		controllers.put(controller.getName(), controller);
		this.arr = controllers.values().toArray(new IEscapyController[0]);
	}

	@Override
	public void attachControllerListener(IEscapyControllerListener listener) {
		log.info("ATTACH CONTROLLER LISTENER: " + listener);
		for (val c: arr) {
			try {
				//noinspection unchecked
				c.addListener(listener);
			} catch (Exception e) {
				log.warning("Controller listener type mismatch!");
			}
		}
	}

	@Override
	public void detachControllerListener(IEscapyControllerListener listener) {
		log.info("DETACH CONTROLLER LISTENER: " + listener);
		for (val c: arr) {
			try {
				//noinspection unchecked
				c.removeListener(listener);
			} catch (Exception e) {
				log.warning("Controller listener type mismatch!");
			}
		}
	}

	@Override
	public void update(float delta) {
		val timestamp = System.nanoTime();
		for (val c : arr) c.update(delta, timestamp);
	}
}
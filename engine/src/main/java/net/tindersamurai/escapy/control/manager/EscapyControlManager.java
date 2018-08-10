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

	private EscapyControlManager() {
		this.controllers = new HashMap<>();
	}

	public static IEscapyControlManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final Map<String, IEscapyController> controllers;
	private IEscapyController[] arr;


	@Override
	public void registerController(IEscapyController controller) {
		controllers.put(controller.getName(), controller);
		this.arr = controllers.values().toArray(new IEscapyController[0]);
	}

	@Override
	public void addControllerListener(IEscapyControllerListener listener) {
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
		for (val c : arr) c.update(delta);
	}
}
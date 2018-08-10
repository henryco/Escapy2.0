package net.tindersamurai.escapy.control;

import lombok.extern.java.Log;
import lombok.val;

import java.util.*;

@Log
public final class EscapyControlManager {

	private static final class LazyHolder {
		private static final EscapyControlManager INSTANCE = new EscapyControlManager();
	}

	private EscapyControlManager() {
		this.controllers = new HashMap<>();
	}

	public static EscapyControlManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final Map<String, IEscapyController> controllers;
	private IEscapyController[] arr;


	public void registerController(IEscapyController controller) {
		controllers.put(controller.getName(), controller);
		this.arr = controllers.values().toArray(new IEscapyController[0]);
	}

	public void update(float delta) {
		for (val c : arr) c.update(delta);
	}
}
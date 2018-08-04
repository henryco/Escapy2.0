package net.tindersamurai.escapy.components.control;

import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.control.IEscapyController;

import java.util.*;

@Log
public final class ControlManager {

	private final Map<String, IEscapyController> controllers;
	private IEscapyController[] arr;

	/* package */ ControlManager() {
		this.controllers = new HashMap<>();
	}

	public void registerController(IEscapyController controller) {
		controllers.put(controller.getName(), controller);
		this.arr = controllers.values().toArray(new IEscapyController[0]);
	}

	public void update() {
		for (val c : arr) c.update();
	}
}
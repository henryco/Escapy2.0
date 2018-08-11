package net.tindersamurai.escapy.components.control;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.control.plain.phys.PhysCharacterListener;
import net.tindersamurai.escapy.control.IEscapyController;
import net.tindersamurai.escapy.control.listener.IEscapyControllerListener;
import net.tindersamurai.escapy.control.keyboard.*;
import net.tindersamurai.escapy.control.manager.IEscapyControlManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.*;

@Provide @Singleton @Log
@EscapyComponentFactory("control")
public class ControlFactory {

	private final IEscapyControlManager controlManager;
	private final Map<String, Object> registered;

	@Inject
	public ControlFactory (
			IEscapyControlManager controlManager
	) {
		this.controlManager = controlManager;
		this.registered = new HashMap<>();

		// initial registering
		new Register().keyboardPhys();
	}


	@EscapyComponentFactory("register")
	public final class Register {

		@EscapyComponent("get")
		public Object get(String name) {
			return registered.get(name);
		}

		@EscapyComponent("char-phys")
		public IEscapyController[] keyboardPhys() {
			val controllers = new IEscapyController[5]; {
				controllers[0] = new EKeyboardInteract("Interact") {{ setKey(Keys.F); }};
				controllers[1] = new EKeyboardMoveLeft("Move left") {{ setKey(Keys.A); }};
				controllers[2] = new EKeyboardMoveRight("Move Right") {{ setKey(Keys.D); }};
				controllers[3] = new EKeyboardRun("Run") {{ setKey(Keys.SHIFT_LEFT); }};
				controllers[4] = new EKeyboardSit("Sit") {{ setKey(Keys.C); }};
			}
			registered.put("char-phys", controllers);
			for (val c : controllers)
				controlManager.registerController(c);
			return controllers;
		}
	}


	@EscapyComponentFactory("listener")
	public final static class Listener {

		@EscapyComponent("phys-character")
		public IEscapyControllerListener physObjectListener (
				@Arg("move") Float move,
				@Arg("run") Float run,
				@Arg("sit") Float sit
		) {
			return new PhysCharacterListener(move, run, sit);
		}
	}


}
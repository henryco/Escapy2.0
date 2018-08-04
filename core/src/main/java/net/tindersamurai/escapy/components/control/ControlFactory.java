package net.tindersamurai.escapy.components.control;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.control.plain.keyboard.KbPhysObjectListener;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.control.IEscapyController;
import net.tindersamurai.escapy.control.IEscapyControllerListener;
import net.tindersamurai.escapy.control.keyboard.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.*;

@Provide @Singleton @Log
@EscapyComponentFactory("control")
public class ControlFactory {

	private final Map<String, Object> registered;

	private final EscapyGameContext gameContext;
	private final ControlManager controlManager;

	@Inject
	public ControlFactory (
			EscapyGameContext gameContext,
			ControlManager controlManager
	) {

		this.registered = new HashMap<>();

		this.gameContext = gameContext;
		this.controlManager = controlManager;

		// initial registering
		for (val phy : new Register().keyboardPhys()) {
			controlManager.registerController(phy);
		}
	}


	@EscapyComponentFactory("register")
	public final class Register {

		@EscapyComponent("get")
		public Object get(String name) {
			return registered.get(name);
		}

		@EscapyComponent("kb-phys")
		public IEscapyController[] keyboardPhys() {
			val controllers = new IEscapyController[5]; {
				controllers[0] = new EKeyboardInteract("Interact") {{ setKey(Keys.F); }};
				controllers[1] = new EKeyboardMoveLeft("Move left") {{ setKey(Keys.A); }};
				controllers[2] = new EKeyboardMoveRight("Move Right") {{ setKey(Keys.D); }};
				controllers[3] = new EKeyboardRun("Run") {{ setKey(Keys.SHIFT_LEFT); }};
				controllers[4] = new EKeyboardSit("Sit") {{ setKey(Keys.C); }};
			}
			registered.put("kb-phys", controllers);
			return controllers;
		}
	}


	@EscapyComponentFactory("listener")
	public final static class Listener {

		@EscapyComponent("kb-phys-object")
		public IEscapyControllerListener physObjectListener (
				@Arg("move") Float move,
				@Arg("run") Float run,
				@Arg("sit") Float sit,
				@Arg("controllers") IEscapyController ... controllers
		) {
			val l = new KbPhysObjectListener(move, run, sit);
			for (val c : controllers) {
				try {
					//noinspection unchecked
					c.setListener(l);
				} catch (Exception e) {
					log.warning("Controller listener type mismatch!");
					log.throwing(this.getClass().getName(), "physObjectListener", e);
				}
			}
			return l;
		}
	}


}
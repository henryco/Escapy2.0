package net.tindersamurai.escapy.components.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import net.tindersamurai.escapy.control.keyboard.EKeyboardInteract;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Provide @Singleton @Log
@EscapyComponentFactory("control")
public class ControlFactory {

	private final Map<String, IEscapyController> registered;

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

		// initializing keys
		new Register().initialize();
	}


	@EscapyComponentFactory("register")
	public final class Register {

		private void initialize() {
			log.info("INITIAL CONTROLLER REGISTRATION");
			keyboardInteract("Interact", Input.Keys.F);
		}

		@EscapyComponent("get")
		public IEscapyController get(String name) {
			return registered.get(name);
		}

		@EscapyComponent("kb-interact")
		public IEscapyController keyboardInteract (
				@Arg("label") String label,
				@Arg("key") Integer key
		) {
			val c =  new EKeyboardInteract(label) {{
				setKey(key);
			}};
			registered.put("kb-interact", c);
			controlManager.registerController(c);
			return c;
		}
	}


	@EscapyComponentFactory("listener")
	public final static class Listener {

		@EscapyComponent("kb-phys-object")
		public IEscapyControllerListener physObjectListener (
				@Arg("controllers") IEscapyController ... controllers
		) {
			val l = new KbPhysObjectListener();
			for (val c : controllers) {
				try {
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
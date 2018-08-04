package net.tindersamurai.escapy.control.keyboard;

import com.badlogic.gdx.Gdx;
import net.tindersamurai.escapy.control.IEscapyController.Interact;

import static net.tindersamurai.escapy.control.IEscapyControllerListener.*;

public class EKeyboardInteract
		extends EscapyKeyboardController<InteractListener>
		implements Interact<Integer> {

	public EKeyboardInteract(String name) {
		super(name);
	}

	@Override
	public void update() {
		if (Gdx.input.isKeyJustPressed(key))
			getListener().onInteract();
	}
}
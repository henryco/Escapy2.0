package net.tindersamurai.escapy.control.keyboard;

import com.badlogic.gdx.Gdx;
import lombok.val;
import net.tindersamurai.escapy.control.IEscapyController.Interact;

import static net.tindersamurai.escapy.control.IEscapyControllerListener.*;

public class EKeyboardInteract
		extends EscapyKeyboardController<InteractListener>
		implements Interact {

	public EKeyboardInteract(String name) {
		super(name);
	}

	@Override
	public void update() {
		for (val l : getListeners()) {
			l.onUpdate();
			if (Gdx.input.isKeyJustPressed(key))
				l.onInteract();
		}
	}
}
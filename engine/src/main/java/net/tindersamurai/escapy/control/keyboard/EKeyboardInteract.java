package net.tindersamurai.escapy.control.keyboard;

import com.badlogic.gdx.Gdx;
import lombok.val;
import net.tindersamurai.escapy.control.IEscapyController.Interact;

import static net.tindersamurai.escapy.control.listener.IEscapyControllerListener.*;

public class EKeyboardInteract
		extends EscapyKeyboardController<InteractListener>
		implements Interact {

	public EKeyboardInteract(String name) {
		super(name);
	}

	@Override
	public void update(float delta, long timestamp) {
		for (val l : getListeners()) {
			l.onUpdate(delta, timestamp);
			if (Gdx.input.isKeyJustPressed(key))
				l.onInteract();
		}
	}
}
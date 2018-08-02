package net.tindersamurai.escapy.controller.keyboard;

import com.badlogic.gdx.Gdx;
import net.tindersamurai.escapy.controller.IEscapyController.Interact;

import static net.tindersamurai.escapy.controller.IEscapyController.Listeners.*;

public class EKeyBoardInteract
		extends AEKeyBoardController<InteractListener>
		implements Interact<Integer> {

	public EKeyBoardInteract(String name) {
		super(name);
	}

	@Override
	public void update() {
		if (Gdx.input.isKeyJustPressed(key))
			getListener().onInteract();
	}
}
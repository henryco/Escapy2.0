package net.tindersamurai.escapy.control.keyboard;

import com.badlogic.gdx.Gdx;
import net.tindersamurai.escapy.control.IEscapyController.MoveLeft;

import static net.tindersamurai.escapy.control.IEscapyControllerListener.*;

public class EKeyboardMoveLeft
		extends EscapyKeyboardController<MoveLeftListener>
		implements MoveLeft<Integer> {

	public EKeyboardMoveLeft(String name) {
		super(name);
	}

	@Override
	public void update() {
		if (Gdx.input.isKeyPressed(key))
			getListener().onMoveLeft();
	}
}
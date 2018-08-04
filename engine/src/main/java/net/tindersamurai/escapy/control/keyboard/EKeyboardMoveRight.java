package net.tindersamurai.escapy.control.keyboard;

import com.badlogic.gdx.Gdx;
import net.tindersamurai.escapy.control.IEscapyController.MoveRight;

import static net.tindersamurai.escapy.control.IEscapyControllerListener.*;

public class EKeyboardMoveRight
		extends EscapyKeyboardController<MoveRightListener>
		implements MoveRight<Integer> {

	public EKeyboardMoveRight(String name) {
		super(name);
	}

	@Override
	public void update() {
		if (Gdx.input.isKeyPressed(key))
			getListener().onMoveRight();
	}
}
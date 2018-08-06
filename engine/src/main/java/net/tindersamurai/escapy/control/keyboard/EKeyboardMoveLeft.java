package net.tindersamurai.escapy.control.keyboard;

import com.badlogic.gdx.Gdx;
import lombok.val;
import net.tindersamurai.escapy.control.IEscapyController.MoveLeft;

import static net.tindersamurai.escapy.control.IEscapyControllerListener.*;

public class EKeyboardMoveLeft
		extends EscapyKeyboardController<MoveLeftListener>
		implements MoveLeft {

	public EKeyboardMoveLeft(String name) {
		super(name);
	}

	@Override
	public void update() {
		for (val l : getListeners()) {
			l.onUpdate();
			if (Gdx.input.isKeyPressed(key))
				l.onMoveLeft();
		}
	}
}
package net.tindersamurai.escapy.control.keyboard;

import com.badlogic.gdx.Gdx;
import lombok.val;
import net.tindersamurai.escapy.control.IEscapyController.MoveLeft;

import static net.tindersamurai.escapy.control.listener.IEscapyControllerListener.*;

public class EKeyboardMoveLeft
		extends EscapyKeyboardController<MoveLeftListener>
		implements MoveLeft {

	public EKeyboardMoveLeft(String name) {
		super(name, MoveLeftListener.class);
	}

	@Override
	public void update(float delta) {
		for (val l : getListeners()) {
			if (Gdx.input.isKeyPressed(key))
				l.onMoveLeft();
		}
	}
}
package net.tindersamurai.escapy.control.keyboard;

import com.badlogic.gdx.Gdx;
import lombok.val;
import net.tindersamurai.escapy.control.IEscapyController.MoveRight;

import static net.tindersamurai.escapy.control.listener.IEscapyControllerListener.*;

public class EKeyboardMoveRight
		extends EscapyKeyboardController<MoveRightListener>
		implements MoveRight {

	public EKeyboardMoveRight(String name) {
		super(name);
	}

	@Override
	public void update(float delta, long timestamp) {
		for (val l : getListeners()) {
			l.onUpdate(delta, timestamp);
			if (Gdx.input.isKeyPressed(key))
				l.onMoveRight();
		}
	}
}
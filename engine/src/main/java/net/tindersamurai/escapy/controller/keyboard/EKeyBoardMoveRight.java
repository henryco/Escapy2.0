package net.tindersamurai.escapy.controller.keyboard;

import com.badlogic.gdx.Gdx;
import net.tindersamurai.escapy.controller.IEscapyController.MoveRight;

import static net.tindersamurai.escapy.controller.IEscapyController.Listeners.*;

public class EKeyBoardMoveRight
		extends AEKeyBoardController<MoveRightListener>
		implements MoveRight<Integer> {

	public EKeyBoardMoveRight(String name) {
		super(name);
	}

	@Override
	public void update() {
		if (Gdx.input.isKeyPressed(key))
			getListener().onMoveRight();
	}
}
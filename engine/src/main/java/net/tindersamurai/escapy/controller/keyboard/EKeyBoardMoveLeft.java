package net.tindersamurai.escapy.controller.keyboard;

import com.badlogic.gdx.Gdx;
import net.tindersamurai.escapy.controller.IEscapyController.MoveLeft;

import static net.tindersamurai.escapy.controller.IEscapyController.Listeners.*;

public class EKeyBoardMoveLeft
		extends AEKeyBoardController<MoveLeftListener>
		implements MoveLeft<Integer> {

	public EKeyBoardMoveLeft(String name) {
		super(name);
	}

	@Override
	public void update() {
		if (Gdx.input.isKeyJustPressed(key))
			getListener().onMoveLeft();
	}
}
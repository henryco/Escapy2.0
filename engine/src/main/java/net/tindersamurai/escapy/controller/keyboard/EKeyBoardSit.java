package net.tindersamurai.escapy.controller.keyboard;

import com.badlogic.gdx.Gdx;
import net.tindersamurai.escapy.controller.IEscapyController.Sit;
import static net.tindersamurai.escapy.controller.IEscapyController.Listeners.*;

public class EKeyBoardSit
		extends AEKeyBoardController<SitListener>
		implements Sit<Integer> {

	public EKeyBoardSit(String name) {
		super(name);
	}

	@Override
	public void update() {
		if (Gdx.input.isKeyPressed(key))
			getListener().onSit();
	}
}
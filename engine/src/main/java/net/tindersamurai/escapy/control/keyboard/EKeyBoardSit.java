package net.tindersamurai.escapy.control.keyboard;

import com.badlogic.gdx.Gdx;
import net.tindersamurai.escapy.control.IEscapyController.Sit;
import static net.tindersamurai.escapy.control.IEscapyControllerListener.*;

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
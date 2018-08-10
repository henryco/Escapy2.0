package net.tindersamurai.escapy.control.keyboard;

import com.badlogic.gdx.Gdx;
import lombok.val;
import net.tindersamurai.escapy.control.IEscapyController.Sit;
import static net.tindersamurai.escapy.control.IEscapyControllerListener.*;

public class EKeyboardSit
		extends EscapyKeyboardController<SitListener>
		implements Sit {

	public EKeyboardSit(String name) {
		super(name);
	}

	@Override
	public void update(float delta) {
		for (val l : getListeners()) {
			l.onUpdate(delta);
			if (Gdx.input.isKeyPressed(key))
				l.onSit();
		}
	}
}
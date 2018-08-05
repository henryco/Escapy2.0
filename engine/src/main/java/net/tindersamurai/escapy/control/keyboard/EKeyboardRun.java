package net.tindersamurai.escapy.control.keyboard;
import com.badlogic.gdx.Gdx;
import net.tindersamurai.escapy.control.IEscapyController.Run;
import static net.tindersamurai.escapy.control.IEscapyControllerListener.*;

public class EKeyboardRun
		extends EscapyKeyboardController<RunListener>
		implements Run {

	public EKeyboardRun(String name) {
		super(name);
	}

	@Override
	public void update() {
		getListener().onUpdate();
		if (Gdx.input.isKeyPressed(key))
			getListener().onRun();
	}
}
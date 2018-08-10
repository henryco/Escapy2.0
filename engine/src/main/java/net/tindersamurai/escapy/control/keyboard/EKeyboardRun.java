package net.tindersamurai.escapy.control.keyboard;
import com.badlogic.gdx.Gdx;
import lombok.val;
import net.tindersamurai.escapy.control.IEscapyController.Run;
import static net.tindersamurai.escapy.control.listener.IEscapyControllerListener.*;

public class EKeyboardRun
		extends EscapyKeyboardController<RunListener>
		implements Run {

	public EKeyboardRun(String name) {
		super(name);
	}

	@Override
	public void update(float delta) {
		for (val l : getListeners()) {
			l.onUpdate(delta);
			if (Gdx.input.isKeyPressed(key))
				l.onRun();
		}
	}
}
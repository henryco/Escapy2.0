package net.tindersamurai.escapy.control.keyboard;
import com.badlogic.gdx.Gdx;
import net.tindersamurai.escapy.control.IEscapyController.Run;
import static net.tindersamurai.escapy.control.IEscapyControllerListener.*;

public class EKeyBoardRun
		extends AEKeyBoardController<RunListener>
		implements Run<Integer> {

	public EKeyBoardRun(String name) {
		super(name);
	}

	@Override
	public void update() {
		if (Gdx.input.isKeyPressed(key))
			getListener().onRun();
	}
}
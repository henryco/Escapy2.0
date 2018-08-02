package net.tindersamurai.escapy.controller.keyboard;
import com.badlogic.gdx.Gdx;
import net.tindersamurai.escapy.controller.IEscapyController.Run;
import static net.tindersamurai.escapy.controller.IEscapyController.Listeners.*;

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
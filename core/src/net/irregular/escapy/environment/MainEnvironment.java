package net.irregular.escapy.environment;

import com.badlogic.gdx.Screen;
import net.irregular.escapy.engine.env.context.game.EscapyGameContext;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;
import javax.inject.Inject;
import java.util.Collection;

/**
 * @author Henry on 28/06/17.
 */
public class MainEnvironment extends EscapyGameContext {

	private final Screen initialScreen;

	@Inject
	protected MainEnvironment(
			Collection<EscapyScreen> screens,
			EscapyScreen initialScreen) {

		super(screens);
		this.initialScreen = initialScreen;
		initialScreen.setScreenContext(this);
	}

	@Override
	protected Screen getInitialScreen() {
		return initialScreen;
	}

}

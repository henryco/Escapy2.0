package net.irregular.escapy.environment;

import net.irregular.escapy.engine.env.context.game.EscapyGameContext;
import net.irregular.escapy.engine.env.context.game.configuration.EscapyGameContextConfiguration;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;

import javax.inject.Inject;
import java.util.Collection;

/**
 * @author Henry on 28/06/17.
 */
public class MainEnvironment extends EscapyGameContext {

	private final EscapyScreen initialScreen;

	@Inject
	protected MainEnvironment(
			Collection<EscapyScreen> escapyScreens,
			EscapyScreen initialScreen,
			EscapyGameContextConfiguration contextConfiguration) {

		super(escapyScreens, contextConfiguration);
		this.initialScreen = initialScreen;
	}

	@Override
	protected EscapyScreen getInitialScreen() {
		return initialScreen;
	}

}

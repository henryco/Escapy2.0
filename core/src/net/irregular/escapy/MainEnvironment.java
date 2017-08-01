package net.irregular.escapy;

import net.irregular.escapy.environment.context.game.EscapyGameContext;
import net.irregular.escapy.environment.context.game.configuration.EscapyGameContextConfiguration;
import net.irregular.escapy.environment.context.screen.EscapyScreen;

import javax.inject.Inject;
import java.util.Collection;

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

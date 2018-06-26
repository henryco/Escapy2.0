package net.tindersamurai.escapy;

import net.tindersamurai.escapy.context.game.EscapyGameContext;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContextConfiguration;
import net.tindersamurai.escapy.context.game.screen.EscapyScreen;

import javax.inject.Inject;
import java.util.Collection;

public final class MainEnvironment extends EscapyGameContext {

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
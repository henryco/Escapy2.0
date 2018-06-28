package net.tindersamurai.escapy.modules;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.context.game.EscapyGameContext;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContextConfiguration;
import net.tindersamurai.escapy.context.game.screen.EscapyScreen;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Provide
public final class MainEnvironment extends EscapyGameContext {

	private final EscapyScreen initialScreen;

	@Inject
	protected MainEnvironment(
			EscapyGameContextConfiguration contextConfiguration,
			@Named("initial-screen") EscapyScreen initialScreen,
			Collection<EscapyScreen> escapyScreens) {

		super(escapyScreens, contextConfiguration);
		this.initialScreen = initialScreen;
	}

	@Override
	protected EscapyScreen getInitialScreen() {
		return initialScreen;
	}

}
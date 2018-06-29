package net.tindersamurai.escapy.components;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.context.game.EscapyGame;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContextConfiguration;
import net.tindersamurai.escapy.context.game.screen.EscapyScreen;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Collection;

@Provide @Singleton
public final class MainEnvironment extends EscapyGame {

	private final EscapyScreen initialScreen;

	@Inject
	public MainEnvironment(
			EscapyGameContextConfiguration contextConfiguration,
			@Named("initial-screen") EscapyScreen initialScreen,
			Collection<EscapyScreen> escapyScreens)
	{
		super(escapyScreens, contextConfiguration);
		this.initialScreen = initialScreen;
	}

	@Override
	protected EscapyScreen getInitialScreen() {
		return initialScreen;
	}

}
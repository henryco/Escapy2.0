package net.tindersamurai.escapy.old.modules;

import dagger.Module;
import dagger.Provides;
import net.tindersamurai.escapy.old.MainEnvironment;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContextConfiguration;
import net.tindersamurai.escapy.context.game.screen.EscapyScreen;
import net.tindersamurai.escapy.old.modules.game.GameScreenModule;
import net.tindersamurai.escapy.old.modules.menu.MenuScreenModule;
import net.tindersamurai.escapy.old.modules.pause.PauseScreenModule;
import net.tindersamurai.escapy.old.modules.splash.SplashScreenModule;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Module(library = true, injects = MainEnvironment.class, includes = {
		SplashScreenModule.class,
		MenuScreenModule.class,
		PauseScreenModule.class,
		GameScreenModule.class
}) @Deprecated
public class MainModule {


	@Provides @Singleton
	protected EscapyScreen provideStartScreen(
			@Named("screen_splash") EscapyScreen screen) {
		return screen;
	}


	@Provides @Singleton
	protected Collection<EscapyScreen> provideScreens(
			@Named("screen_menu") EscapyScreen menuScreen,
			@Named("screen_pause") EscapyScreen pauseScreen,
			@Named("screen_game") EscapyScreen gameScreen) {

		List<EscapyScreen> screenList = new ArrayList<>();
		screenList.add(menuScreen);
		screenList.add(pauseScreen);
		screenList.add(gameScreen);
		return screenList;
	}

	@Provides @Singleton
	protected EscapyGameContextConfiguration provideConfiguration() {
		return new MainConfiguration();
	}

}
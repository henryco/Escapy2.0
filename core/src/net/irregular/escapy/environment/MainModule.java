package net.irregular.escapy.environment;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.MainEnvironment;
import net.irregular.escapy.env.context.game.configuration.EscapyGameContextConfiguration;
import net.irregular.escapy.env.context.screen.EscapyScreen;
import net.irregular.escapy.environment.game.GameScreenModule;
import net.irregular.escapy.environment.menu.MenuScreenModule;
import net.irregular.escapy.environment.pause.PauseScreenModule;
import net.irregular.escapy.environment.splash.SplashScreenModule;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Henry on 28/06/17.
 */
@Module(library = true, injects = MainEnvironment.class, includes = {
		SplashScreenModule.class,
		MenuScreenModule.class,
		PauseScreenModule.class,
		GameScreenModule.class
})
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

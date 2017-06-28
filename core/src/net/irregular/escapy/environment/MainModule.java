package net.irregular.escapy.environment;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.environment.modules.GameScreenModule;
import net.irregular.escapy.environment.modules.MenuScreenModule;
import net.irregular.escapy.environment.modules.PauseScreenModule;
import net.irregular.escapy.environment.modules.SplashScreenModule;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;


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
		GameScreenModule.class,
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

}

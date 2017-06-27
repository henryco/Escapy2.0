package net.irregular.escapy;

import com.badlogic.gdx.Screen;
import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.environment.screen.menu.MenuScreen;
import net.irregular.escapy.environment.screen.splash.SplashScreen;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Henry on 28/06/17.
 */
@Module(injects = {EscapyMainEnvironment.class}, library = true)
public class MainEnvironmentModule {

	@Provides @Singleton @Named("screen_splash")
	public Screen provideSplashScreen() {
		return new SplashScreen();
	}

	@Provides @Singleton @Named("screen_menu")
	public Screen provideMenuScreen() {
		return new MenuScreen();
	}


}

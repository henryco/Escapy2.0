package net.irregular.escapy;

import com.badlogic.gdx.Screen;
import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.dagger.modules.SplashScreenModule;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Henry on 28/06/17.
 */
@Module(library = true,
		includes = {
				SplashScreenModule.class
		},
		injects = {
				EscapyMainEnvironment.class
		}
)
public class MainEnvironmentModule {


	@Provides @Singleton @Named("screen_start")
	public Screen provideSplashScreen(@Named("screen_splash") Screen screen) {
		return screen;
	}


}

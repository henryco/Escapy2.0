package net.irregular.escapy.environment;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.environment.modules.SplashScreenModule;
import net.irregular.escapy.engine.env.EscapyScreen;


import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Henry on 28/06/17.
 */
@Module(library = true, injects = MainEnvironment.class, includes = {
		SplashScreenModule.class,
		//todo more modules
})
public class MainModule {

	@Provides @Singleton
	public EscapyScreen provideStartScreen(@Named("screen_splash") EscapyScreen screen) {
		return screen;
	}

}

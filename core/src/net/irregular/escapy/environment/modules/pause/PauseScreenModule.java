package net.irregular.escapy.environment.modules.pause;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Henry on 28/06/17.
 */
@Module(library = true)
public class PauseScreenModule {

	@Provides @Singleton @Named("screen_pause")
	public EscapyScreen providesPauseScreen() {
		return new PauseScreen();
	}

}

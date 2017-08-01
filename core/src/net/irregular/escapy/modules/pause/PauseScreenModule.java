package net.irregular.escapy.modules.pause;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.context.game.screen.EscapyScreen;

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
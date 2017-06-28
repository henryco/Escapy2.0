package net.irregular.escapy.environment.modules.game;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Henry on 28/06/17.
 */
@Module(library = true)
public class GameScreenModule {

	@Provides @Singleton @Named("screen_game")
	public EscapyScreen providesGameScreen() {
		return new GameScreen();
	}

}

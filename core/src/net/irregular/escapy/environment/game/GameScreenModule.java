package net.irregular.escapy.environment.game;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;
import net.irregular.escapy.engine.graphic.camera.EscapyCamera;
import net.irregular.escapy.engine.group.container.EscapyGroupContainer;
import net.irregular.escapy.environment.group.GroupModule;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Henry on 28/06/17.
 */
@Module(library = true, includes = {
		GroupModule.class,
})
public class GameScreenModule {

	@Provides @Singleton @Named("screen_game")
	public EscapyScreen providesGameScreen(@Named("default_camera") EscapyCamera camera,
										   EscapyGroupContainer groupContainer) {
		return new GameScreen(groupContainer, camera);
	}

}

package net.irregular.escapy.modules.game;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.context.game.screen.EscapyScreen;
import net.irregular.escapy.graphic.camera.EscapyCamera;
import net.irregular.escapy.group.container.EscapyGroupContainer;
import net.irregular.escapy.modules.group.GroupModule;

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

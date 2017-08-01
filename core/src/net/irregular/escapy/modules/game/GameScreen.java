package net.irregular.escapy.modules.game;

import net.irregular.escapy.context.annotation.ScreenName;
import net.irregular.escapy.context.game.screen.EscapyScreen;
import net.irregular.escapy.context.game.screen.EscapyScreenContext;
import net.irregular.escapy.graphic.camera.EscapyCamera;
import net.irregular.escapy.graphic.render.light.source.LightSource;
import net.irregular.escapy.group.container.EscapyGroupContainer;

/**
 * @author Henry on 28/06/17.
 */
@ScreenName("game_screen")
public class GameScreen implements EscapyScreen {

	private EscapyScreenContext screenContext;

	private final EscapyGroupContainer groupContainer;
	private final EscapyCamera camera;


	LightSource source;

	public GameScreen(EscapyGroupContainer groupContainer,
					  EscapyCamera camera) {
		this.groupContainer = groupContainer;
		this.camera = camera;

	}

	@Override
	public void show() {
		source = groupContainer.getRendererContainer()
				.getRendererAttribute("lights_foreground:light1");

	}


	@Override
	public void render(float delta) {
		groupContainer.getRendererContainer().render(delta);
	}



	private void update(float delta) {
		System.out.println("update");
	}


	@Override
	public void resize(int width, int height) {
		groupContainer.getRendererContainer().resize(width, height);
	}


	@Override
	public void dispose() {
		groupContainer.getLocationContainer().getLocation().dispose();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void setScreenContext(EscapyScreenContext screenContext) {
		this.screenContext = screenContext;
	}


}

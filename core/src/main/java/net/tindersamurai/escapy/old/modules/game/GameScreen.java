package net.tindersamurai.escapy.old.modules.game;

import net.tindersamurai.escapy.context.annotation.ScreenName;
import net.tindersamurai.escapy.context.game.screen.EscapyScreen;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenContext;
import net.tindersamurai.escapy.graphic.camera.EscapyCamera;
import net.tindersamurai.escapy.graphic.render.light.source.LightSource;
import net.tindersamurai.escapy.group.container.EscapyGroupContainer;

/**
 * @author Henry on 28/06/17.
 */
@ScreenName("game_screen")
public class GameScreen implements EscapyScreen {

	private EscapyScreenContext screenContext;

	private final EscapyGroupContainer group;
	private final EscapyCamera camera;


	LightSource source;

	public GameScreen(EscapyGroupContainer group,
					  EscapyCamera camera) {
		this.group = group;
		this.camera = camera;

	}

	@Override
	public void show() {
		source = group.getRendererContainer().getRendererAttribute("lights_foreground:light1");
	}


	@Override
	public void render(float delta) {
		group.getRendererContainer().render(delta);
	}



	private void update(float delta) {
		System.out.println("update");
	}


	@Override
	public void resize(int width, int height) {
		group.getRendererContainer().resize(width, height);
	}


	@Override
	public void dispose() {
		group.getLocationContainer().getLocation().dispose();
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

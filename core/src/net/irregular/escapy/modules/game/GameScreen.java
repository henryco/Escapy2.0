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
		LightSource source = group.getRendererContainer().getRendererAttribute("lights_foreground:light1");
		System.out.println(source.name);
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

package net.irregular.escapy.environment.main.game;

import net.irregular.escapy.engine.env.context.annotation.ScreenName;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;
import net.irregular.escapy.engine.env.context.screen.EscapyScreenContext;
import net.irregular.escapy.engine.graphic.camera.EscapyCamera;
import net.irregular.escapy.engine.graphic.render.program.shader.proxy.LightSource;
import net.irregular.escapy.engine.group.container.EscapyGroupContainer;

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
		source = groupContainer.getRenderContainer().getRendererAttribute("lights_foreground:light1");
	}

	@Override
	public void render(float delta) {
//		camera.update(() -> camera.translateCamera(3f * delta, 0));
//		float[] position = source.getPosition();
//		source.setPosition(position[0] - 1, position[1]);
//		source.translate(1, 0);
		groupContainer.getRenderContainer().render(delta);
	}

	@Override
	public void resize(int width, int height) {
		groupContainer.getRenderContainer().resize(width, height);
	}


	@Override
	public void dispose() {
		groupContainer.getMapContainer().getLocation().dispose();
	}


	@Override
	public void setScreenContext(EscapyScreenContext screenContext) {
		this.screenContext = screenContext;
	}


}

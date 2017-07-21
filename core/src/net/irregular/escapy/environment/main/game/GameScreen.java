package net.irregular.escapy.environment.main.game;

import net.irregular.escapy.engine.env.context.annotation.ScreenName;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;
import net.irregular.escapy.engine.env.context.screen.EscapyScreenContext;
import net.irregular.escapy.engine.group.container.EscapyGroupContainer;

/**
 * @author Henry on 28/06/17.
 */
@ScreenName("game_screen")
public class GameScreen implements EscapyScreen {


	private final EscapyGroupContainer groupContainer;
	private EscapyScreenContext screenContext;

	public GameScreen(EscapyGroupContainer groupContainer) {
		this.groupContainer = groupContainer;
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		groupContainer.getRenderContainer().render(delta);
	}

	@Override
	public void resize(int width, int height) {
		groupContainer.getRenderContainer().resize(width, height);
	}


	@Override
	public void dispose() {
		groupContainer.getMapContainer().getLogation().dispose();
	}


	@Override
	public void setScreenContext(EscapyScreenContext screenContext) {
		this.screenContext = screenContext;
	}


}

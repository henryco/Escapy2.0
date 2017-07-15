package net.irregular.escapy.environment.main.menu;

import net.irregular.escapy.engine.env.context.annotation.ScreenName;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;
import net.irregular.escapy.engine.env.context.screen.EscapyScreenContext;
import net.irregular.escapy.environment.main.game.GameScreen;

/**
 * @author Henry on 28/06/17.
 */
@ScreenName("menu_screen") // TODO: 28/06/17 we will work on menu screen later
public class MenuScreen implements EscapyScreen {

	private EscapyScreenContext context;

	@Override
	public void show() {

		context.setScreen(GameScreen.class);
	}

	@Override
	public void render(float delta) {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void setScreenContext(EscapyScreenContext screenContext) {
		this.context = screenContext;
	}
}

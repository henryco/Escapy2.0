package net.irregular.escapy.environment.modules.menu;

import net.irregular.escapy.engine.env.context.annotation.ScreenName;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;
import net.irregular.escapy.engine.env.context.screen.EscapyScreenContext;

/**
 * @author Henry on 28/06/17.
 */
@ScreenName("menu_test_name")
public class MenuScreen implements EscapyScreen {

	@Override
	public void show() {

		System.out.println("MENU LOADED");
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

	}
}

package net.tindersamurai.escapy.components.screen;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenCore;

import javax.inject.Singleton;

@Provide("menu-screen")
public class MenuScreen extends EscapyScreenCore {


	@Override
	public void show() {
		// todo SOME INITIALIZATION
		setScreen(GameScreen.class);
	}

	@Override
	public void render(float delta) {
		// todo SOME MENU
	}

}

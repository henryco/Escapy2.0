package net.tindersamurai.escapy.modules.menu;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenCore;
import net.tindersamurai.escapy.modules.game.GameScreen;

import javax.inject.Singleton;

@Provide("menu-screen") @Singleton
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

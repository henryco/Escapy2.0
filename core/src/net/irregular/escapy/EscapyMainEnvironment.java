package net.irregular.escapy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Henry on 28/06/17.
 */
public class EscapyMainEnvironment extends Game {


	private Screen screen;

	@Inject
	public EscapyMainEnvironment(@Named("screen_menu") Screen screen) {
		this.screen = screen;
	}


	@Override
	public void create() {
		setScreen(screen);
	}


	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}

	@Override
	public Screen getScreen() {
		return super.getScreen();
	}

}

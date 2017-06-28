package net.irregular.escapy.environment;

import com.badlogic.gdx.Game;
import net.irregular.escapy.engine.env.EscapyScreen;

import javax.inject.Inject;

/**
 * @author Henry on 28/06/17.
 */
public class MainEnvironment extends Game {

	private EscapyScreen screen;

	@Inject
	public MainEnvironment(EscapyScreen screen) {
		this.screen = screen;
		screen.setGameContext(this);
	}


	@Override
	public void create() {
		setScreen(screen);
	}


}

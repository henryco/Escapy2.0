package net.tindersamurai.escapy.context.game.screen;


import com.badlogic.gdx.Screen;
import net.tindersamurai.escapy.context.game.EscapyGame;

/**
 * @author Henry on 28/06/17.
 */
public interface EscapyScreenContext {

	/**
	 * @return Actual screen
	 */
	Screen getScreen();

	/**
	 * @param name Screen name
	 * @return {@link EscapyScreen} or null if screen does not exist
	 */
	EscapyScreen getScreen(String name);

	EscapyScreen getScreen(Class<? extends EscapyScreen> screenClass);

	/**
	 * Set actual screen
	 */
	void setScreen(EscapyScreen screen);

	void setScreen(String name);

	void setScreen(Class<? extends EscapyScreen> screenClass);

	EscapyGame getGame();
}

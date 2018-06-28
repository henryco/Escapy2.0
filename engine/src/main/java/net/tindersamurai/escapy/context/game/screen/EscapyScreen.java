package net.tindersamurai.escapy.context.game.screen;

import com.badlogic.gdx.Screen;
import java.util.logging.Logger;

/**
 * @author Henry on 28/06/17.
 */
public interface EscapyScreen extends Screen {

	final class logger {
		public static final Logger log = Logger.getLogger(EscapyScreen.class.getName());
	}

	void setScreenContext(EscapyScreenContext screenContext);

	default void pause() {
		logger.log.info(this.getClass().getSimpleName());
	}
	default void resume() {
		logger.log.info(this.getClass().getSimpleName());
	}
	default void hide() {
		logger.log.info(this.getClass().getSimpleName());
	}
	default void dispose() {
		logger.log.info(this.getClass().getSimpleName());
	}
}

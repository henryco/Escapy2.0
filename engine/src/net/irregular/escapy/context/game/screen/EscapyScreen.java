package net.irregular.escapy.context.game.screen;

import com.badlogic.gdx.Screen;

/**
 * @author Henry on 28/06/17.
 */
public interface EscapyScreen extends Screen {

	void setScreenContext(EscapyScreenContext screenContext);

	default void pause() {
		System.out.println("Pause: "+this.getClass().getSimpleName());
	}
	default void resume() {
		System.out.println("Resume: "+this.getClass().getSimpleName());
	}
	default void hide() {
		System.out.println("Hide: "+this.getClass().getSimpleName());
	}
	default void dispose() {
		System.out.println("Dispose: "+this.getClass().getSimpleName());
	}
}

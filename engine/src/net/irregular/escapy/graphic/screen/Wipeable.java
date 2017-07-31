package net.irregular.escapy.graphic.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import net.irregular.escapy.env.context.annotation.EscapyAPI;

/**
 * @author Henry on 29/06/17.
 */
public interface Wipeable {

	default void color(float r, float g, float b, float a) {
		Gdx.gl.glClearColor(r, g, b, a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@EscapyAPI default void wipe() {
		color(0f,0f,0f,0f);
	}

	@EscapyAPI default void clear() {
		color(0f,0f,0f,1f);
	}

}

package net.irregular.escapy.engine.graphic.render.port.gl10;

import com.badlogic.gdx.graphics.GL30;

/**
 * @author Henry on 29/06/17.
 */
public class GLBlend {

	public static final int[] ADD_RGB = new int[]{GL30.GL_SRC_ALPHA, GL30.GL_ONE, GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_COLOR};
	public static final int[] ADD_RGBA = new int[]{GL30.GL_SRC_ALPHA, GL30.GL_ONE, GL30.GL_ONE, GL30.GL_ONE_MINUS_SRC_COLOR};
}

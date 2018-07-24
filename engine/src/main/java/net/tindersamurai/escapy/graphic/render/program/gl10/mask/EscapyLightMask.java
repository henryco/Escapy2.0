package net.tindersamurai.escapy.graphic.render.program.gl10.mask;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;

public interface EscapyLightMask {

	int[] MULTIPLY = new int[]{GL30.GL_DST_COLOR, GL30.GL_ONE_MINUS_SRC_ALPHA};
	int[] SEPIA = new int[]{GL30.GL_SRC_ALPHA, GL30.GL_DST_COLOR};
	int[] SCREEN = new int[]{GL30.GL_ONE_MINUS_DST_COLOR, GL30.GL_ONE};
	int[] LINEAR_DODGE = new int[]{GL30.GL_ONE, GL30.GL_ONE};

	void setMaskFunc(int src, int dst);

	void setMaskFunc(int[] func);

	void setColor(float r, float g, float b, float a);

	void setColor(int r, int g, int b, int a);

	void setColor(int[] color);

	void renderMask(Texture target);
}
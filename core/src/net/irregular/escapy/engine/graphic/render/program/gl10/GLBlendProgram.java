package net.irregular.escapy.engine.graphic.render.program.gl10;

import com.badlogic.gdx.graphics.GL30;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;

/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI
public interface GLBlendProgram {

	@EscapyAPI int[] ADD_RGB = new int[]{GL30.GL_SRC_ALPHA, GL30.GL_ONE, GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_COLOR};
	@EscapyAPI int[] ADD_RGBA = new int[]{GL30.GL_SRC_ALPHA, GL30.GL_ONE, GL30.GL_ONE, GL30.GL_ONE_MINUS_SRC_COLOR};
}

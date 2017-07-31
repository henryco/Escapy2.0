package net.irregular.escapy.engine.graphic.render.program.gl10.blend;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Batch;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;

/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI
public interface EscapyGLBlendRenderer {

 	void begin(Batch batch);
 	void end(Batch batch);

	default void blend(Batch batch, Runnable drawClosure) {
		begin(batch);
		drawClosure.run();
		end(batch);
	}

	void setColorBlendMode(int[] colorBlendMode);
	int[] getColorBlendMode();


 	interface Separate {

 		@EscapyAPI static int[] ADD_RGB() {
			return new int[]{GL30.GL_SRC_ALPHA, GL30.GL_ONE, GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_COLOR};
		}

		@EscapyAPI static int[] ADD_RGBA(){
 			return new int[]{GL30.GL_SRC_ALPHA, GL30.GL_ONE, GL30.GL_ONE, GL30.GL_ONE_MINUS_SRC_COLOR};
		}

	}

}
package net.irregular.escapy.engine.graphic.render.program.gl10;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;

import java.util.function.Consumer;

/**
 * @author Henry on 30/06/17.
 */ @EscapyAPI
public class EscapyNativeBlendRenderer implements GLBlendProgram {

	private int[] blendMode;
	private final Batch batch;


	@EscapyAPI
	public EscapyNativeBlendRenderer() {
		this(ADD_RGBA);
	}
	@EscapyAPI
	public EscapyNativeBlendRenderer(int[] blendMode) {
		setColorBlendMode(blendMode);
		this.batch = new SpriteBatch();
	}


	@EscapyAPI
	public void blend(Consumer<Batch> batchConsumer) {

		int srcFunc = batch.getBlendSrcFunc();
		int dstFunc = batch.getBlendDstFunc();

		batch.begin();
		batch.enableBlending();

		setProgram(blendMode);

		batchConsumer.accept(batch);

		batch.disableBlending();
		batch.end();

		batch.begin();
		batch.setBlendFunction(srcFunc, dstFunc);
		batch.end();
	}


	public void setColorBlendMode(int[] colorBlendMode) {
		this.blendMode = colorBlendMode.clone();
	}

	@EscapyAPI public int[] getColorBlendMode() {
		return this.blendMode.clone();
	}


	private void setProgram(int[] program) {
		this.setProgram(program[0], program[1], program[2], program[3]);
	}
	private void setProgram(int p1, int p2, int p3, int p4) {
		batch.setBlendFunction(-1, -1);
		Gdx.gl30.glBlendFuncSeparate(p1, p2, p3, p4);
		Gdx.gl30.glBlendEquationSeparate(GL30.GL_FUNC_ADD, GL30.GL_FUNC_ADD);
	}


}

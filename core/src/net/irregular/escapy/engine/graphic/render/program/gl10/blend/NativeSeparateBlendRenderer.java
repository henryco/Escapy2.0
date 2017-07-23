package net.irregular.escapy.engine.graphic.render.program.gl10.blend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;

import java.util.function.Consumer;

/**
 * @author Henry on 30/06/17.
 */ @EscapyAPI
public class NativeSeparateBlendRenderer implements EscapyGLBlendRenderer {

	private final int[] blendMode;
	private final Batch batch;

	@EscapyAPI
	public NativeSeparateBlendRenderer() {
		this(Separate.ADD_RGBA());
	}
	@EscapyAPI
	public NativeSeparateBlendRenderer(int[] blendMode) {
		this.blendMode = new int[4];
		this.batch = new SpriteBatch();
		setColorBlendMode(blendMode);
	}


	@Override
	public void blend(Consumer<Batch> batchConsumer) {

		int srcFunc = batch.getBlendSrcFunc();
		int dstFunc = batch.getBlendDstFunc();

		batch.begin();
		batch.enableBlending();

		setBlendFunction(blendMode, batch);

		batchConsumer.accept(batch);

		batch.disableBlending();
		batch.end();

		batch.begin();
		batch.setBlendFunction(srcFunc, dstFunc);
		batch.end();
	}

	@Override
	public void setColorBlendMode(int[] colorBlendMode) {
		System.arraycopy(colorBlendMode, 0, blendMode, 0, 4);
	}

	@Override
	public int[] getColorBlendMode() {
		return this.blendMode.clone();
	}


	private void setBlendFunction(int[] program, Batch batch) {
		batch.setBlendFunction(-1, -1);
		Gdx.gl20.glBlendFuncSeparate(program[0], program[1], program[2], program[3]);
		Gdx.gl20.glBlendEquationSeparate(GL30.GL_FUNC_ADD, GL30.GL_FUNC_ADD);
	}


}

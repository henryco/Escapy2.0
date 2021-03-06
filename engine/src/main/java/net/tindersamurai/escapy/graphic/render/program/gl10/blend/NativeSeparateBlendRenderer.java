package net.tindersamurai.escapy.graphic.render.program.gl10.blend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Batch;
import net.tindersamurai.escapy.context.annotation.EscapyAPI;


/**
 * @author Henry on 30/06/17.
 */ @EscapyAPI
public class NativeSeparateBlendRenderer implements EscapyGLBlendRenderer {

	private final int[] blendMode;
	private int srcFunc, dstFunc;

	private boolean blend;

	@EscapyAPI
	public NativeSeparateBlendRenderer() {
		this(Separate.ADD_RGBA());
	}

	public NativeSeparateBlendRenderer(int[] blendMode) {
		this.blendMode = new int[4];
		setColorBlendMode(blendMode);
		reset();
	}


	@Override
	public synchronized void begin(Batch batch) {

		if (blend) throw new RuntimeException("You cannot call EscapyGLBlendRenderer.BEGIN() before call END()");

		this.srcFunc = batch.getBlendSrcFunc();
		this.dstFunc = batch.getBlendDstFunc();

		batch.begin();
		batch.enableBlending();

		setBlendFunction(blendMode, batch);
		this.blend = true;
	}


	@Override
	public synchronized void end(Batch batch) {

		if (!blend) throw new RuntimeException("You cannot call EscapyGLBlendRenderer.END() before call BEGIN()");

		batch.disableBlending();
		batch.end();

		batch.begin();
		batch.setBlendFunction(this.srcFunc, this.dstFunc);
		batch.end();

		reset();
	}


	private void setBlendFunction(int[] program, Batch batch) {
		batch.setBlendFunction(-1, -1);
		Gdx.gl20.glBlendFuncSeparate(program[0], program[1], program[2], program[3]);
		Gdx.gl20.glBlendEquationSeparate(GL30.GL_FUNC_ADD, GL30.GL_FUNC_ADD);
	}

	private void reset() {
		this.dstFunc = -1;
		this.srcFunc = -1;
		this.blend = false;
	}


	@Override
	public void setColorBlendMode(int[] colorBlendMode) {
		System.arraycopy(colorBlendMode, 0, blendMode, 0, 4);
	}

	@Override
	public int[] getColorBlendMode() {
		return this.blendMode.clone();
	}

}
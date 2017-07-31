package net.irregular.escapy.engine.graphic.render.fbo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.graphic.screen.Resolution;

/**
 * This class encapsulate default FBO logic provided by GDX.FrameBuffer
 * @author Henry on 29/06/17.
 */ @EscapyAPI
public class EscapyFrameBuffer implements EscapyFBO {

	private final FrameBuffer buffer;
	private final Sprite bufferSprite;
	private final TextureRegion bufferRegion;

	/**
	 * This class encapsulate default FBO logic provided by GDX.FrameBuffer
	 * @author Henry on 29/06/17.
	 */ @EscapyAPI
	public EscapyFrameBuffer(final FrameBuffer buffer, boolean initialWipe) {
		this.buffer = buffer;
		this.bufferRegion = new TextureRegion(buffer.getColorBufferTexture());
		this.bufferSprite = new Sprite(bufferRegion);
		setFlip(false, true);
		if (initialWipe) begin(this::wipe);
	}

	/**
	 * This class encapsulate default FBO logic provided by GDX.FrameBuffer
	 * @author Henry on 29/06/17.
	 */ @EscapyAPI
	public EscapyFrameBuffer(final Resolution resolution, boolean initialWipe) {
		this(new FrameBuffer(Pixmap.Format.RGBA8888, resolution.width, resolution.height, resolution.bool), initialWipe);
	}

	/**
	 * This class encapsulate default FBO logic provided by GDX.FrameBuffer
	 * @author Henry on 29/06/17.
	 */ @EscapyAPI
	public EscapyFrameBuffer(final FrameBuffer buffer) {
	 	this(buffer, false);
	}

	/**
	 * This class encapsulate default FBO logic provided by GDX.FrameBuffer
	 * @author Henry on 29/06/17.
	 */ @EscapyAPI
	public EscapyFrameBuffer(final Resolution resolution) {
	 	this(resolution, false);
	}

	/**
	 * This class encapsulate default FBO logic provided by GDX.FrameBuffer
	 * @author Henry on 29/06/17.
	 */ @EscapyAPI
	public EscapyFrameBuffer() {
	 	this(new Resolution(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
	}



	@Override
	public void begin() {
		buffer.begin();
	}

	@Override
	public void end() {
		buffer.end();
	}

	@Override
	public float getWidth() {
		return buffer.getWidth();
	}

	@Override
	public float getHeight() {
		return buffer.getHeight();
	}

	@Override
	public void setFlip(boolean x, boolean y) {
		bufferSprite.setFlip(x, y);
		bufferRegion.flip(x, y);
	}

	@Override
	public void renderGraphics(Batch batch) {
		batch.begin();
	 	bufferSprite.draw(batch);
	 	batch.end();
	}

	@Override
	public TextureRegion getTextureRegion() {
		return bufferRegion;
	}

	@Override
	public Texture getTexture() {
		return buffer.getColorBufferTexture();
	}

	@Override
	public Sprite getSprite() {
		return bufferSprite;
	}


	@Override
	public void dispose() {
		if (buffer != null) buffer.dispose();
	}
}

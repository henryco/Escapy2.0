package net.irregular.escapy.engine.graphic.render.fbo;

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
	public EscapyFrameBuffer(final FrameBuffer buffer) {
		this.buffer = buffer;
		this.bufferRegion = new TextureRegion(buffer.getColorBufferTexture());
		this.bufferSprite = new Sprite(bufferRegion);
	}
	/**
	 * This class encapsulate default FBO logic provided by GDX.FrameBuffer
	 * @author Henry on 29/06/17.
	 */ @EscapyAPI
	public EscapyFrameBuffer(final Resolution resolution) {
		this(new FrameBuffer(Pixmap.Format.RGBA8888, resolution.width, resolution.height, resolution.bool));
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


}

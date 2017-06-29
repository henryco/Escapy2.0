package net.irregular.escapy.engine.graphic.render.fbo;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import net.irregular.escapy.engine.graphic.screen.Resolution;

/**
 * This class encapsulate default FBO logic provided by GDX.FrameBuffer
 * @author Henry on 29/06/17.
 */
public class EscapyFrameBuffer implements EscapyFBO {

	private final FrameBuffer buffer;
	private final Sprite bufferSprite;
	private final TextureRegion bufferRegion;

	public EscapyFrameBuffer(final FrameBuffer buffer) {
		this.buffer = buffer;
		this.bufferRegion = new TextureRegion(buffer.getColorBufferTexture());
		this.bufferSprite = new Sprite(bufferRegion);
	}
	public EscapyFrameBuffer(final Resolution resolution) {
		this(resolution, false);
	}
	public EscapyFrameBuffer(final Resolution resolution, boolean depth) {
		this(new FrameBuffer(Pixmap.Format.RGBA8888, resolution.width, resolution.height, depth));
	}


	public EscapyFrameBuffer begin(Runnable r) {
		begin();
		r.run();
		end();
		return this;
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
		bufferSprite.draw(batch);
	}

	@Override
	public TextureRegion getTextureRegion() {
		return bufferRegion;
	}

	@Override
	public Sprite getSprite() {
		return bufferSprite;
	}
}

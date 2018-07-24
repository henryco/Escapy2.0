package net.tindersamurai.escapy.graphic.render.fbo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.tindersamurai.escapy.context.annotation.EscapyAPI;
import net.tindersamurai.escapy.graphic.render.mapping.GraphicRenderer;
import net.tindersamurai.escapy.graphic.screen.Wipeable;
import net.tindersamurai.escapy.utils.EscapyObject;

/**
 * @author Henry on 29/06/17.
 */
public interface EscapyFBO extends Wipeable, EscapyObject {

	@EscapyAPI default EscapyFBO begin(Runnable r) {
		begin();
		r.run();
		end();
		return this;
	}

	default EscapyFBO begin(Batch batch, Runnable r) {
		begin();
		batch.begin();
		r.run();
		batch.end();
		end();
		return this;
	}


	default String getName() {
		return this.getClass().getSimpleName()+"[" + hashCode() + "]";
	}

	void begin();
	void end();

	float getWidth();
	float getHeight();

	@EscapyAPI TextureRegion getTextureRegion();
	@EscapyAPI Texture getTexture();
	@EscapyAPI Sprite getSprite();

	void setFlip(boolean x, boolean y);

	default void draw(Batch batch) {
		batch.begin();
		getSprite().draw(batch);
		batch.end();
	}
}
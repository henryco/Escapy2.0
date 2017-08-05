package net.irregular.escapy.graphic.render.fbo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.irregular.escapy.context.annotation.EscapyAPI;
import net.irregular.escapy.graphic.render.mapping.GraphicRenderer;
import net.irregular.escapy.graphic.screen.Wipeable;
import net.irregular.escapy.utils.EscapyObject;

/**
 * @author Henry on 29/06/17.
 */
public interface EscapyFBO extends GraphicRenderer, Wipeable, EscapyObject {

	@EscapyAPI default EscapyFBO begin(Runnable r) {
		begin();
		r.run();
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
}
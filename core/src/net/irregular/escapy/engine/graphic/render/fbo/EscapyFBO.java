package net.irregular.escapy.engine.graphic.render.fbo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.env.utils.Named;
import net.irregular.escapy.engine.graphic.render.mapping.GraphicRenderer;
import net.irregular.escapy.engine.graphic.screen.Wipeable;

/**
 * @author Henry on 29/06/17.
 */
public interface EscapyFBO extends GraphicRenderer, Wipeable, Named {

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
}

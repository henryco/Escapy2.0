package net.irregular.escapy.engine.graphic.render.fbo;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.irregular.escapy.engine.graphic.render.mapping.GraphicRenderer;

/**
 * @author Henry on 29/06/17.
 */
public interface EscapyFBO extends GraphicRenderer {

	void begin();
	void end();

	float getWidth();
	float getHeight();

	TextureRegion getTextureRegion();
	Sprite getSprite();
}

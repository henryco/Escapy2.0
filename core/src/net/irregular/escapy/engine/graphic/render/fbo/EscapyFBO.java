package net.irregular.escapy.engine.graphic.render.fbo;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.graphic.render.mapping.GraphicRenderer;
import net.irregular.escapy.engine.graphic.screen.Wipeable;

/**
 * @author Henry on 29/06/17.
 */
public interface EscapyFBO extends GraphicRenderer, Wipeable {

	void begin();
	void end();

	float getWidth();
	float getHeight();

	@EscapyAPI TextureRegion getTextureRegion();
	@EscapyAPI Sprite getSprite();
}

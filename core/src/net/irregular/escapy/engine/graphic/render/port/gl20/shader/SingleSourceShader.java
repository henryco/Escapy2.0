package net.irregular.escapy.engine.graphic.render.port.gl20.shader;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Henry on 29/06/17.
 */
public interface SingleSourceShader extends EscapyShader {

	void setSourceName(String sourceName);
	void draw(Batch batch, float x, float y, Texture source);
	void draw(Batch batch, float x, float y, float width, float height, TextureRegion source);
	void draw(Batch batch, Sprite source);

}

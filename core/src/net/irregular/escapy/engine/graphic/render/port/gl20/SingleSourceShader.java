package net.irregular.escapy.engine.graphic.render.port.gl20;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Henry on 29/06/17.
 */
public interface SingleSourceShader extends EscapyShader {

	void setSourceName(String sourceName);
	void draw(Batch batch, Texture source);
	void draw(Batch batch, TextureRegion source);
	void draw(Batch batch, Sprite source);

}

package net.tindersamurai.escapy.graphic.render.light.processor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.tindersamurai.escapy.utils.EscapyObject;

/**
 * @author Henry on 27/07/17.
 */
public interface EscapyLightProcessor extends EscapyObject {

	void draw(Batch batch, float x, float y, Texture colorMap, Texture normalMap, Texture maskMap);

	void draw(Batch batch, Sprite colorMap, Sprite normalMap, Sprite maskMap);

	void draw(Batch batch, float x, float y, float width, float height,
			  TextureRegion colorMap, TextureRegion normalMap, TextureRegion maskMap);

	void setFieldSize(float width, float height);
	Float[] getFieldSize();

	void setThreshold(float threshold);
	void setEnable(boolean enable);

	float getThreshold();
	boolean isEnable();
}

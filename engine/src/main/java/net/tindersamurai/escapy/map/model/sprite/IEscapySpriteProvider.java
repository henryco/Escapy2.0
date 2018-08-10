package net.tindersamurai.escapy.map.model.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface IEscapySpriteProvider {

	Sprite getDiffuseSprite();
	Sprite getNormalsSprite();
	Sprite getShadowsSprite();

}
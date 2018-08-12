package net.tindersamurai.escapy.map.model.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import net.tindersamurai.escapy.graphic.IEscapyRenderable;

public interface IEscapySpriteProvider extends IEscapyRenderable {

	Sprite getDiffuseSprite();
	Sprite getNormalsSprite();
	Sprite getShadowsSprite();

}
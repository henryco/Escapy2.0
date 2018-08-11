package net.tindersamurai.escapy.map.model.texture;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.logging.Logger;

public interface IEscapyTextureData {

	float getX();
	float getY();
	float getScaleX();
	float getScaleY();
	float getWidth();
	float getHeight();
	float getRotation();
	boolean isFlipX();
	boolean isFlipY();


	default void initializeSprite (Sprite sprite) {
		if (sprite == null) return;

		Logger.getLogger(getClass().getName()).info("Initialize sprite: " + sprite);

		sprite.setScale(getScaleX(), getScaleY());
		sprite.setFlip(isFlipX(), isFlipY());
		sprite.setPosition(getX(), getY());
		sprite.setRotation(getRotation());

		sprite.setSize (
				getWidth() == 0 ? sprite.getWidth() : getWidth(),
				getHeight() == 0 ? sprite.getHeight() : getHeight()
		);
	}
}
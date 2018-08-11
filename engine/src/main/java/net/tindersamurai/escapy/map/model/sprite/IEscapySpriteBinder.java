package net.tindersamurai.escapy.map.model.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import net.tindersamurai.escapy.map.model.texture.IEscapyTextureData;

import java.util.function.Consumer;

public interface IEscapySpriteBinder extends IEscapyTextureData {

	void apply(Consumer<Sprite> s);
	Sprite getEffectiveSprite();

	default float[] getBindPadding() {
		return new float[] {0, 0};
	}

	default void setBindPadding(float left, float top) {}

	@Override
	default float getX() {
		return getEffectiveSprite().getX();
	}

	@Override
	default float getY() {
		return getEffectiveSprite().getY();
	}

	@Override
	default float getScaleX() {
		return getEffectiveSprite().getScaleX();
	}

	@Override
	default float getScaleY() {
		return getEffectiveSprite().getScaleY();
	}

	@Override
	default float getWidth() {
		return getEffectiveSprite().getWidth();
	}

	@Override
	default float getHeight() {
		return getEffectiveSprite().getHeight();
	}

	@Override
	default boolean isFlipX() {
		return getEffectiveSprite().isFlipX();
	}

	@Override
	default boolean isFlipY() {
		return getEffectiveSprite().isFlipY();
	}

	@Override
	default float getRotation() {
		return getEffectiveSprite().getRotation();
	}
}
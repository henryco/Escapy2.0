package net.tindersamurai.escapy.map.model.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import net.tindersamurai.escapy.map.model.texture.IEscapyTextureData;

import java.util.function.Consumer;

public interface IEscapySpriteProvider extends IEscapyTextureData {

	void apply(Consumer<Sprite> s);
	Sprite provideEffectiveSprite();

	default float[] getBindPadding() {
		return new float[] {0, 0};
	}

	void setBindPadding(float left, float top);

	@Override
	default float getX() {
		return provideEffectiveSprite().getX();
	}

	@Override
	default float getY() {
		return provideEffectiveSprite().getY();
	}

	@Override
	default float getScaleX() {
		return provideEffectiveSprite().getScaleX();
	}

	@Override
	default float getScaleY() {
		return provideEffectiveSprite().getScaleY();
	}

	@Override
	default float getWidth() {
		return provideEffectiveSprite().getWidth();
	}

	@Override
	default float getHeight() {
		return provideEffectiveSprite().getHeight();
	}

	@Override
	default boolean isFlipX() {
		return provideEffectiveSprite().isFlipX();
	}

	@Override
	default boolean isFlipY() {
		return provideEffectiveSprite().isFlipY();
	}

	@Override
	default float getRotation() {
		return provideEffectiveSprite().getRotation();
	}
}
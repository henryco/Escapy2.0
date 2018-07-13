package net.tindersamurai.escapy.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.lang.reflect.Constructor;

public final class EscapyUtils {

	public static Void _void() {
		try {
			Constructor<Void> constructor = Void.class.getDeclaredConstructor();
			constructor.setAccessible(true);
			return constructor.newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	public static Sprite centerize(Sprite sprite, float w, float h) {
		final float sx = (w - (sprite.getWidth() * sprite.getScaleX())) * 0.5f;
		final float sy = (h - (sprite.getHeight() * sprite.getScaleY())) * 0.5f;
		sprite.setPosition(sx, sy);
//		System.out.println("CENTER: " + sx + " : " + sy);
		return sprite;
	}
}
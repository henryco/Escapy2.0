package net.tindersamurai.escapy.utils;

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
}
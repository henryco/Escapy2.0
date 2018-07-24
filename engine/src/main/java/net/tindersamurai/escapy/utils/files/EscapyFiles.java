package net.tindersamurai.escapy.utils.files;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.logging.Logger;

import static java.io.File.separator;

/**
 * @author Henry on 26/07/17.
 */ // TODO: 26/07/17
public interface EscapyFiles {

	static String safetyPath(String path) {
		return path.replace("\\", separator).replace("/", separator);
	}

	static Texture loadTexture(String file) {
		Logger log = Logger.getLogger(EscapyFiles.class.getName());
		if (file == null || file.isEmpty()) {
			log.warning("Texture file: " + file);
			return null;
		}
		try {
			return new Texture(Gdx.files.internal(safetyPath(file)));
		} catch (Exception e) {
			log.throwing(EscapyFiles.class.getName(), "load", e);
			e.printStackTrace();
			return null;
		}
	}

	static Sprite loadSprite(String file) {
		final Texture t;
		if ((t = loadTexture(file)) != null)
			return new Sprite(t);
		return null;
	}
}
package net.tindersamurai.escapy.components.stage.plain;

import com.badlogic.gdx.Gdx;
import net.tindersamurai.escapy.map.location.IEscapyLocationHandler;

import java.util.function.Consumer;
import java.util.logging.Logger;

public interface LocationLoader {

	boolean loadLocation();

	default void loadLocation(Consumer<Boolean> onResult) {
		Logger logger = Logger.getLogger(this.toString());
		new Thread(() -> {
			try {
				Gdx.app.postRunnable(() -> onResult.accept(loadLocation()));
			} catch (Exception e) {
				logger.throwing(
						IEscapyLocationHandler.class.getName(),
						"switchLocation", e
				);
				onResult.accept(false);
			}
		}).start();
	}
}
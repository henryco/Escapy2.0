package net.tindersamurai.escapy.components.screen;

import com.badlogic.gdx.utils.GdxNativesLoader;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.components.stage.plain.LocationLoader;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenCore;

import javax.inject.Inject;

@Provide("loading-screen")
public class LoadingScreen extends EscapyScreenCore {

	private final LocationLoader locationLoader;

	@Inject
	public LoadingScreen(LocationLoader locationLoader) {
		this.locationLoader = locationLoader;
	}

	@Override
	public void show() {
		locationLoader.loadLocation(loaded -> {
			if (!loaded) throw new RuntimeException("ERROR LOADING");
			LoadingScreen.this.setScreen(GameScreen.class);
		});
	}

	@Override
	public void render(float delta) {
		// todo
	}

}
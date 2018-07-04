package net.tindersamurai.escapy.deprecated.modules.group.util;

import dagger.Module;
import dagger.Provides;
import net.tindersamurai.escapy.graphic.camera.EscapyCamera;
import net.tindersamurai.escapy.graphic.screen.Resolution;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Henry on 21/07/17.
 */
@Module(library = true)
public class CameraModule {


	@Provides @Singleton @Named("default_camera")
	public EscapyCamera provideCamera(Resolution resolution) {
		return new EscapyCamera(resolution);
	}


	@Provides
	public Resolution provideResolution() {
//		return new Resolution(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		return new Resolution(1280, 720);
	}

}

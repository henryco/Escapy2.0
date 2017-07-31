package net.irregular.escapy.environment.group.util;

import com.badlogic.gdx.Gdx;
import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.graphic.camera.EscapyCamera;
import net.irregular.escapy.engine.graphic.screen.Resolution;

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
		return new Resolution(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

}

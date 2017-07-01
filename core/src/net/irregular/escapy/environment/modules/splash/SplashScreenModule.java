package net.irregular.escapy.environment.modules.splash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;
import net.irregular.escapy.engine.graphic.camera.EscapyCamera;
import net.irregular.escapy.engine.graphic.screen.Resolution;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Henry on 28/06/17.
 */
@Module(library = true)
public class SplashScreenModule {

	@Provides @Singleton @Named("screen_splash")
	public EscapyScreen provideSplashScreen(
			@Named("camera_splash") EscapyCamera camera,
			@Named("logo_splash") String logo,
			@Named("batch_splash") Batch batch,
			@Named("time_splash") float time) {

		return new SplashScreen(logo, camera, batch, time);
	}




	@Provides @Singleton @Named("camera_splash")
	public EscapyCamera provideSplashCamera(
			@Named("resolution_splash") Resolution resolution) {
		return new EscapyCamera(resolution);
	}


	@Provides @Singleton @Named("resolution_splash")
	public Resolution provideSplashScreenResolution() {
		return new Resolution(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}


	@Provides @Named("logo_splash")
	String provideSplashLogoUrl() {
		return "ESCAPY.png";
	}


	@Provides @Singleton @Named("batch_splash")
	Batch provideBatch() {
		return new SpriteBatch();
	}


	@Provides @Named("time_splash")
	float provideSplashTime() {
		return 3f;
	}

}

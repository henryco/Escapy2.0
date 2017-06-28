package net.irregular.escapy.environment.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dagger.Module;
import dagger.Provides;

import net.irregular.escapy.engine.graphic.camera.EscapyCamera;
import net.irregular.escapy.engine.graphic.camera.Resolution;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;
import net.irregular.escapy.environment.screen.SplashScreen;

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
			@Named("time_splash") float time,
			@Named("name_splash") String name,
			@Named("next_screen") String nextScreenName) {

		return new SplashScreen(logo, camera, batch, time, name, nextScreenName);
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



	@Provides @Named("name_splash")
	String provideScreenName() {
		return "splashScreen";
	}

	@Provides @Named("next_screen")
	String provideNextScreenName() {
		return "menuScreen";
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

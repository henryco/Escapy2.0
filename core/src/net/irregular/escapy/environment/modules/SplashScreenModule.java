package net.irregular.escapy.environment.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dagger.Module;
import dagger.Provides;

import net.irregular.escapy.engine.graphic.camera.EscapyCamera;
import net.irregular.escapy.engine.graphic.camera.Resolution;
import net.irregular.escapy.engine.env.EscapyScreen;
import net.irregular.escapy.environment.screen.SplashScreen;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Henry on 28/06/17.
 */
@Module(library = true, includes = {
		MenuScreenModule.class
})
public class SplashScreenModule {


	@Provides @Singleton @Named("screen_splash")
	public EscapyScreen provideSplashScreen(
			@Named("camera_splash") EscapyCamera camera,
			@Named("logo_splash") String logo,
			@Named("screen_menu") EscapyScreen menuScreen,
			@Named("batch_splash") Batch batch,
			@Named("time_splash") float time) {
		return new SplashScreen(logo, camera, batch, time, menuScreen);
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

package net.irregular.escapy.dagger.modules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.environment.camera.EscapyCamera;
import net.irregular.escapy.environment.camera.Resolution;
import net.irregular.escapy.environment.screen.splash.SplashScreen;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Henry on 28/06/17.
 */
@Module(library = true, injects = SplashScreenModule.class)
public class SplashScreenModule {



	@Provides @Singleton
	@Named("screen_splash")
	public Screen provideSplashScreen(
			@Named("camera_splash") EscapyCamera camera,
			@Named("logo_splash") String logo) {

		return new SplashScreen(logo, camera);
	}


	@Provides @Singleton
	@Named("camera_splash")
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

}

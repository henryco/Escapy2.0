package net.irregular.escapy.modules.splash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.context.game.screen.EscapyScreen;
import net.irregular.escapy.graphic.camera.EscapyCamera;
import net.irregular.escapy.graphic.screen.Resolution;
import net.irregular.escapy.group.container.EscapyGroupContainer;
import net.irregular.escapy.modules.group.GroupModule;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.File;

/**
 * @author Henry on 28/06/17.
 */
@Module(library = true, includes = {GroupModule.class})
public class SplashScreenModule {


	@Provides @Singleton @Named("screen_splash")
	public EscapyScreen provideSplashScreen(

			EscapyGroupContainer groupContainer,

			@Named("camera_splash") EscapyCamera camera,
			@Named("logo_splash") String logo,
			@Named("batch_splash") Batch batch,
			@Named("time_splash") float time
	) {
		return new SplashScreen(logo, camera, batch, time, groupContainer);
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
		return "res" + File.separator + "ESCAPY.png";
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

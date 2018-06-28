package net.tindersamurai.escapy.modules.splash;

import com.badlogic.gdx.Gdx;
import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.graphic.camera.EscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.screen.Resolution;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.File;

@Module
public final class SplashModule {

	@Provide @Singleton @Named("camera_splash")
	public IEscapyCamera provideSplashCamera() {
		return new EscapyCamera(new Resolution(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
	}

	@Provide @Named("logo_splash")
	String provideSplashLogoUrl() {
		return "res" + File.separator + "ESCAPY.png";
	}

	@Provide @Named("time_splash")
	float provideSplashTime() {
		return 3f;
	}

}
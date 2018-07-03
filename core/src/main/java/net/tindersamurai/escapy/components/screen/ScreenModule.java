package net.tindersamurai.escapy.components.screen;

import com.badlogic.gdx.Gdx;
import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.components.config.ConfigModule;
import net.tindersamurai.escapy.components.stage.StageModule;
import net.tindersamurai.escapy.graphic.camera.EscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.screen.Resolution;

import javax.inject.Singleton;
import java.io.File;

@Module(componentsRootClass = ScreenModule.class,
		include = { ConfigModule.class, StageModule.class }
) public final class ScreenModule {

	@Provide("camera_splash") @Singleton
	public IEscapyCamera provideSplashCamera() {
		return new EscapyCamera(new Resolution(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
	}

	@Provide("logo_splash") @Singleton
	public String provideSplashLogoUrl() {
		return "res" + File.separator + "ESCAPY.png";
	}

	@Provide("time_splash") @Singleton
	public float provideSplashTime() {
		return 3f;
	}

}
package net.tindersamurai.escapy.components.screen;

import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.components.config.ConfigModule;
import net.tindersamurai.escapy.components.stage.StageModule;
import net.tindersamurai.escapy.map.location.IEscapyLocation;
import net.tindersamurai.escapy.map.location.IEscapyLocationHandler;

import javax.inject.Singleton;
import java.io.File;

@Module(componentsRootClass = ScreenModule.class,
		include = { ConfigModule.class, StageModule.class }
) public final class ScreenModule {

	@Provide("logo_splash") @Singleton
	public String provideSplashLogoUrl() {
		return "res" + File.separator + "ESCAPY.png";
	}

	@Provide("time_splash") @Singleton
	public float provideSplashTime() {
		return 3f;
	}

	@Provide
	public IEscapyLocation provideLocation (
			IEscapyLocationHandler handler
	) {
		return handler.getLocation();
	}

}
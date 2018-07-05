package net.tindersamurai.escapy.components;

import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.context.game.screen.EscapyScreen;
import net.tindersamurai.escapy.components.config.ConfigModule;
import net.tindersamurai.escapy.components.screen.ScreenModule;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;

@Module(componentsRootClass = MainModule.class,
		targetsRootPath = "net.tindersamurai.escapy",
		include = { ConfigModule.class, ScreenModule.class }
) public final class MainModule {

	@Provide("initial-screen") @Singleton
	public EscapyScreen provideInitialScreen(
			@Named("splash-screen") EscapyScreen screen
	) {
		return screen;
	}

	@Provide @Singleton
	public Collection<EscapyScreen> provideScreens(
			@Named("splash-screen") EscapyScreen splash,
			@Named("menu-screen") EscapyScreen menu,
			@Named("game-screen") EscapyScreen game
	) {
		return new ArrayList<EscapyScreen>() {{
			add(splash);
			add(menu);
			add(game);
		}};
	}

}
package net.tindersamurai.escapy.modules;

import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.context.game.screen.EscapyScreen;
import net.tindersamurai.escapy.modules.screen.GameModule;
import net.tindersamurai.escapy.modules.screen.MenuModule;
import net.tindersamurai.escapy.modules.screen.SplashModule;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collection;

@Module(
		componentsRootPath = "net.tindersamurai.escapy.components",
		targetsRootPath = "net.tindersamurai.escapy",
		include = {
				SplashModule.class,
				MenuModule.class,
				GameModule.class
		}
) public class MainModule {

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
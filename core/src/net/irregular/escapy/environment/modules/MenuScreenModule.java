package net.irregular.escapy.environment.modules;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.env.EscapyScreen;
import net.irregular.escapy.environment.screen.MenuScreen;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Henry on 28/06/17.
 */
@Module(library = true)
public class MenuScreenModule {


	@Provides @Named("screen_menu") @Singleton
	public EscapyScreen provideMenuScreen() {

		return new MenuScreen();
	}



}

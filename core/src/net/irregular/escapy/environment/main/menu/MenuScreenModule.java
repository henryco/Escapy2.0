package net.irregular.escapy.environment.main.menu;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.env.context.screen.EscapyScreen;

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

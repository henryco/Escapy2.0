package net.irregular.escapy.modules.menu;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.context.game.screen.EscapyScreen;
import net.irregular.escapy.group.container.EscapyGroupContainer;
import net.irregular.escapy.modules.group.GroupModule;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Henry on 28/06/17.
 */
@Module(library = true, includes = {
		GroupModule.class
})
public class MenuScreenModule {


	@Provides @Named("screen_menu") @Singleton
	public EscapyScreen provideMenuScreen(EscapyGroupContainer groupContainer) {
		return new MenuScreen(groupContainer);
	}



}

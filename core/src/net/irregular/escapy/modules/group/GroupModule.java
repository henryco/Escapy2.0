package net.irregular.escapy.modules.group;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.group.container.EscapyGroupContainer;
import net.irregular.escapy.group.container.imp.DefaultGroupContainer;
import net.irregular.escapy.group.map.loader.imp.DefaultLocationLoader;
import net.irregular.escapy.group.render.loader.imp.DefaultRendererLoader;
import net.irregular.escapy.modules.group.location.LocationsModule;
import net.irregular.escapy.modules.group.renderer.RendererModule;

import javax.inject.Singleton;

/**
 * @author Henry on 21/07/17.
 */
@Module(library = true, includes = {
		LocationsModule.class,
		RendererModule.class
})
public class GroupModule {


	@Provides @Singleton
	public EscapyGroupContainer provideGroupContainer(

			DefaultRendererLoader rendererLoader,
			DefaultLocationLoader locationLoader,
			String configFileName
	) {
		return new DefaultGroupContainer(configFileName, locationLoader, rendererLoader);
	}


	@Provides
	protected String provideConfigName() {
		return "GameConfiguration.json";
	}


}
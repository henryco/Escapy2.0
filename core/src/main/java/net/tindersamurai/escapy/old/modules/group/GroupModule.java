package net.tindersamurai.escapy.old.modules.group;

import dagger.Module;
import dagger.Provides;
import net.tindersamurai.escapy.group.container.EscapyGroupContainer;
import net.tindersamurai.escapy.group.container.imp.DefaultGroupContainer;
import net.tindersamurai.escapy.group.map.loader.imp.DefaultLocationLoader;
import net.tindersamurai.escapy.group.render.loader.imp.DefaultRendererLoader;
import net.tindersamurai.escapy.old.modules.group.location.LocationsModule;
import net.tindersamurai.escapy.old.modules.group.renderer.RendererModule;

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
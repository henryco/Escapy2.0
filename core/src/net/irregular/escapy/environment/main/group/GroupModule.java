package net.irregular.escapy.environment.main.group;

import dagger.Module;
import dagger.Provides;
import net.irregular.escapy.engine.group.container.EscapyGroupContainer;
import net.irregular.escapy.engine.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.engine.group.map.loader.LocationLoader;
import net.irregular.escapy.engine.group.render.loader.RendererLoader;
import net.irregular.escapy.environment.main.group.location.LocationsModule;
import net.irregular.escapy.environment.main.group.renderer.RendererModule;

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

			RendererLoader<EscapySubLocation> rendererLoader,
			LocationLoader locationLoader,
			String configFileName
	) {
		return new EscapyGroupContainer(configFileName, locationLoader, rendererLoader);
	}


	@Provides
	protected String provideConfigName() {
		return "GameConfiguration.json";
	}


}
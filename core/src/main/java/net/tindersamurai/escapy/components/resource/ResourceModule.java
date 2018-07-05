package net.tindersamurai.escapy.components.resource;

import com.github.henryco.injector.meta.annotations.Module;
import net.tindersamurai.escapy.components.config.ConfigModule;

@Module(componentsRootClass = ResourceModule.class,
		include = { ConfigModule.class }
) public final class ResourceModule {

}
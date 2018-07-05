package net.tindersamurai.escapy.components.location;

import com.github.henryco.injector.meta.annotations.Module;
import net.tindersamurai.escapy.components.config.ConfigModule;
import net.tindersamurai.escapy.components.model.ModelModule;
import net.tindersamurai.escapy.components.node.NodeModule;

@Module(componentsRootClass = LocationModule.class,
		include = {
			ConfigModule.class,
			NodeModule.class,
			ModelModule.class
		}
) public final class LocationModule {

}
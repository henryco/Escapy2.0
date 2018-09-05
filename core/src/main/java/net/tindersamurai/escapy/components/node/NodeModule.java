package net.tindersamurai.escapy.components.node;

import com.github.henryco.injector.meta.annotations.Module;
import net.tindersamurai.escapy.components.config.ConfigModule;

@Module(componentsRootClass = NodeModule.class,
		include = { ConfigModule.class }
) public final class NodeModule {

}
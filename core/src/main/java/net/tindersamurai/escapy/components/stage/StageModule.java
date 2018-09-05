package net.tindersamurai.escapy.components.stage;

import com.github.henryco.injector.meta.annotations.Module;
import net.tindersamurai.escapy.components.config.ConfigModule;
import net.tindersamurai.escapy.components.factory.FactoryModule;

@Module(componentsRootClass = StageModule.class,
		include = { ConfigModule.class, FactoryModule.class }
) public final class StageModule {


}
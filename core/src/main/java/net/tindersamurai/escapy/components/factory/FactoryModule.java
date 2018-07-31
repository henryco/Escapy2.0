package net.tindersamurai.escapy.components.factory;

import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.activecomponent.parser.XmlStreamComponentParser;
import net.tindersamurai.escapy.components.config.ConfigModule;
import net.tindersamurai.escapy.components.model.ModelFactory;
import net.tindersamurai.escapy.components.model.ModelModule;
import net.tindersamurai.escapy.components.node.NodeFactory;
import net.tindersamurai.escapy.components.node.NodeModule;
import net.tindersamurai.escapy.components.physics.PhysicsFactory;
import net.tindersamurai.escapy.components.physics.PhysicsModule;
import net.tindersamurai.escapy.components.resource.ResourceModule;

import javax.inject.Singleton;

@Module(componentsRootClass = FactoryModule.class,
		include = {
			ConfigModule.class,
			NodeModule.class,
			ResourceModule.class,
			ModelModule.class,
			PhysicsModule.class
		}
) public final class FactoryModule {

	@Provide public EscapyComponentParser provideComponentParser (
			ResourceFactory resourcesConfigFactory,
			LocationFactory locationFactory,
			PhysicsFactory physicsFactory,
			ModelFactory modelFactory,
			NodeFactory nodeFactory
	) {
		return new XmlStreamComponentParser(
				resourcesConfigFactory,
				locationFactory,
				physicsFactory,
				modelFactory,
				nodeFactory
		);
	}

}
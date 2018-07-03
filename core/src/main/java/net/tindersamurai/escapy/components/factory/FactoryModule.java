package net.tindersamurai.escapy.components.factory;

import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.activecomponent.parser.XmlStreamComponentParser;
import net.tindersamurai.escapy.components.config.ConfigModule;
import net.tindersamurai.escapy.components.node.NodeModule;
import net.tindersamurai.escapy.components.resource.ResourceModule;

import javax.inject.Singleton;

@Module(componentsRootClass = FactoryModule.class,
		include = { ConfigModule.class, NodeModule.class, ResourceModule.class }
) public final class FactoryModule {

	@Provide @Singleton
	public EscapyComponentParser provideComponentParser (
			ResourceFactory resourcesConfigFactory,
			LocationFactory locationFactory,
			NodeFactory nodeFactory
	) {
		return new XmlStreamComponentParser(
				resourcesConfigFactory,
				locationFactory,
				nodeFactory
		);
	}

}
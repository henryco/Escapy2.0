package net.tindersamurai.escapy.modules.nested;

import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.activecomponent.parser.XmlStreamComponentParser;
import net.tindersamurai.escapy.components.factory.LocationFactory;
import net.tindersamurai.escapy.components.factory.MainResourcesConfigFactory;

import javax.inject.Singleton;

@Module(componentsRootPath =
		"net.tindersamurai.escapy.components.factory"
) public final class FactoryModule {

	@Provide @Singleton
	public EscapyComponentParser provideComponentParser (
			MainResourcesConfigFactory resourcesConfigFactory,
			LocationFactory locationFactory
	) {
		return new XmlStreamComponentParser(
				resourcesConfigFactory,
				locationFactory
		);
	}

}
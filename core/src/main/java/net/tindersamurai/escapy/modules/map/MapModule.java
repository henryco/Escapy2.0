package net.tindersamurai.escapy.modules.map;

import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.activecomponent.parser.XmlStreamComponentParser;
import net.tindersamurai.escapy.components.map.MapFactory;

import javax.inject.Singleton;

@Module(componentsRootPath =
		"net.tindersamurai.escapy.components.map"
) public final class MapModule {

	@Provide @Singleton
	public EscapyComponentParser provideComponentParser(
			MapFactory mapFactory
	) {
		return new XmlStreamComponentParser(mapFactory);
	}

}
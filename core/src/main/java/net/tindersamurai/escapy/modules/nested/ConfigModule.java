package net.tindersamurai.escapy.modules.nested;

import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.activecomponent.parser.XmlStreamComponentParser;
import net.tindersamurai.escapy.components.config.GameResourcesConfig;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContextConfiguration;

import javax.inject.Singleton;

@Module(componentsRootPath =
		"net.tindersamurai.escapy.components.config"
) public final class ConfigModule {

	@Provide @Singleton
	public EscapyComponentParser provideComponentParser (
			GameResourcesConfig resourcesFactory
	) {
		return new XmlStreamComponentParser(resourcesFactory);
	}

	@Provide("game-resources-config-file")
	public String provideResourcesConfigFile (
			EscapyGameContextConfiguration contextConfiguration
	) {
		return contextConfiguration.getConfigsFilePath() + "config.eacxml";
	}
}
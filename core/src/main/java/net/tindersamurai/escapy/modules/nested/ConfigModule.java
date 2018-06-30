package net.tindersamurai.escapy.modules.nested;

import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.activecomponent.parser.XmlStreamComponentParser;
import net.tindersamurai.escapy.components.factory.MainResourcesConfigFactory;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;

import javax.inject.Singleton;

@Module(componentsRootPath =
		"net.tindersamurai.escapy.components.config",
		include = { FactoryModule.class }
) public final class ConfigModule {

	@Provide("MainResConfigFile")
	public String provideResourcesConfigFile (
			EscapyGameContext gameContext
	) {
		return gameContext.getConfigsFilePath() + "config.eacxml";
	}

	@Provide("DefaultStageFileName") @Singleton
	public String provideDefaultStageFileName() {
		return "index.eacxml";
	}

}
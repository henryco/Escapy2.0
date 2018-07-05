package net.tindersamurai.escapy.components.config;

import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;

import javax.inject.Singleton;

@Module(componentsRootClass = ConfigModule.class
) public final class ConfigModule {

	@Provide("MainResConfigFile") @Singleton
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
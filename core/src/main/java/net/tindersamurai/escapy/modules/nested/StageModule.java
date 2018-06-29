package net.tindersamurai.escapy.modules.nested;

import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.map.stage.IEscapyLocationContainer;
import net.tindersamurai.escapy.map.stage.IEscapyStage;

import javax.inject.Singleton;
import java.util.function.Function;

import static java.io.File.*;

@Module(
		componentsRootPath = "net.tindersamurai.escapy.components.stage",
		include = {ConfigModule.class}
) public final class StageModule {


	/**
	 * @see net.tindersamurai.escapy.components.config.GameResourcesConfig
	 */ @Provide @Singleton
	public IEscapyStage[] provideStages (
			@Arg("game-resources-config-file") String configFile,
			EscapyComponentParser parser
	) {
		return parser.parseComponent(configFile);
	}


	@Provide @Singleton
	public Function<String, IEscapyLocationContainer> locationContainerFactory (
			EscapyComponentParser parser,
			EscapyGameContext gameContext
	) {
	 	return name -> parser.parseComponent(gameContext.getConfigsFilePath() + separator + name);
	}


}
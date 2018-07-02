package net.tindersamurai.escapy.modules.nested;

import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.escapy.components.config.MainResourcesConfig;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import net.tindersamurai.escapy.utils.EscapyUtils;

import javax.inject.Singleton;

@Module(componentsRootPath =
		"net.tindersamurai.escapy.components.config",
		include = { LocationModule.class }
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


	// FIXME REMOVE
	@Provide @Singleton public Void test (
			EscapyComponentParser parser,
			MainResourcesConfig config
	) {
		System.out.println("TEST");
		val file = config.getDefaultStage().getLocations()[0].getFile().getUrl();
		System.out.println("FILE: "+ file);
		IEscapyNode node = parser.parseComponent(file);
		System.out.println(node.treeView());
		return EscapyUtils._void();
	}
}
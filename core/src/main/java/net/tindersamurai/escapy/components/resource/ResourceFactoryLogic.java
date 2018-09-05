package net.tindersamurai.escapy.components.resource;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.utils.files.EscapyFileMetaData;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Provide @Singleton
public class ResourceFactoryLogic {

	private final EscapyGameContext gameContext;

	@Inject
	public ResourceFactoryLogic(EscapyGameContext gameContext) {
		this.gameContext = gameContext;
	}

	public EscapyFileMetaData fileMetaData(String id, String url, String name) {
		return new EscapyFileMetaData(id, url, name);
	}

	public Map<String, Object> main(EscapyFileMetaData[] stages, String defaultStage) {
		if (defaultStage == null)
			throw new RuntimeException("Default stage REQUIRED!");
		if (stages == null)
			throw new RuntimeException("Stages not found");
		return new HashMap<String, Object>() {{
			put("default", defaultStage);
			put("stages", stages);
		}};
	}

	public Map<String, Object> stage(EscapyFileMetaData[] locations, String def) {
		if (def == null)
			throw new RuntimeException("Default location REQUIRED!");
		if (locations == null)
			throw new RuntimeException("Locations not found");
		return new HashMap<String, Object> () {{
			put("locations", locations);
			put("default", def);
		}};
	}

	public String configPath() {
		return gameContext.getConfigsFilePath();
	}

}
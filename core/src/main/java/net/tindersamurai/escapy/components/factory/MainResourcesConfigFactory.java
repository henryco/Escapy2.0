package net.tindersamurai.escapy.components.factory;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.utils.files.EscapyFileMetaData;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Provide @Singleton
@EscapyComponentFactory("config")
public final class MainResourcesConfigFactory {

	@EscapyComponent("metadata")
	public EscapyFileMetaData fileMetaData(
			@Arg("id") String id,
			@Arg("url") String url,
			@Arg("title") String name
	) {
		return new EscapyFileMetaData(id, url, name);
	}

	@EscapyComponent("main")
	public Map<String, Object> main (
			@Arg("stages") EscapyFileMetaData[] stages,
			@Arg("default") String defaultStage
	) {
		if (defaultStage == null)
			throw new RuntimeException("Default stage REQUIRED!");
		if (stages == null)
			throw new RuntimeException("Stages not found");
		return new HashMap<String, Object> () {{
			put("default", defaultStage);
			put("stages", stages);
		}};
	}


	@EscapyComponent("stage")
	public Map<String, Object> stage (
			@Arg("locations") EscapyFileMetaData[] locations,
			@Arg("default") String def
	) {
		if (def == null)
			throw new RuntimeException("Default location REQUIRED!");
		if (locations == null)
			throw new RuntimeException("Locations not found");
		return new HashMap<String, Object> () {{
			put("locations", locations);
			put("default", def);
		}};
	}

}
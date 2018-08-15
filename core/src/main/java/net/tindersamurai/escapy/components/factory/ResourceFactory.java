package net.tindersamurai.escapy.components.factory;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.resource.ResourceFactoryLogic;
import net.tindersamurai.escapy.utils.files.EscapyFileMetaData;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Provide @Singleton
@EscapyComponentFactory("resource")
public class ResourceFactory {

	private final ResourceFactoryLogic logic;

	@Inject
	public ResourceFactory(ResourceFactoryLogic logic) {
		this.logic = logic;
	}

	@EscapyComponent("metadata")
	public EscapyFileMetaData fileMetaData(
			@Arg("id") String id,
			@Arg("url") String url,
			@Arg("title") String name
	) {
		return logic.fileMetaData(id, url, name);
	}

	@EscapyComponent("main")
	public Map<String, Object> main (
			@Arg("stages") EscapyFileMetaData[] stages,
			@Arg("default") String defaultStage
	) {
		return logic.main(stages, defaultStage);
	}

	@EscapyComponent("stage")
	public Map<String, Object> stage (
			@Arg("locations") EscapyFileMetaData[] locations,
			@Arg("default") String def
	) {
		return logic.stage(locations, def);
	}

	@EscapyComponent("config-path")
	public String configPath() {
		return logic.configPath();
	}

}
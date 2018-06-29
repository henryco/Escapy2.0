package net.tindersamurai.escapy.components.stage;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.map.stage.IEscapyLocationContainer;
import net.tindersamurai.escapy.map.stage.IEscapyStage;
import net.tindersamurai.escapy.map.stage.IEscapyStageContainer;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.io.File.separator;

@Provide @Singleton
public class EscapyStageContainer implements IEscapyStageContainer {

	private final Function<String, IEscapyLocationContainer> locationFactory;
	private final Map<String, IEscapyStage> stages;
	private final IEscapyStage defaultStage;

	private IEscapyStage currentStage;


	@Inject
	public EscapyStageContainer (
			Function<String, IEscapyLocationContainer> locationFactory,
			IEscapyStage[] stages
	) {

		this.locationFactory = locationFactory;
		this.defaultStage = findDefault(stages);
		this.currentStage = defaultStage;

		this.stages = new HashMap<>();
		for (IEscapyStage stage : stages)
			this.stages.put(stage.getName(), stage);

		loadStage();
	}


	@Override
	public IEscapyStage[] getStages() {
		return stages.values().toArray(new IEscapyStage[0]);
	}

	@Override
	public IEscapyStage getDefault() {
		return defaultStage;
	}

	@Override
	public IEscapyStage getCurrent() {
		return currentStage;
	}

	@Override
	public IEscapyLocationContainer loadStage(String name) {
		this.currentStage = stages.get(name);
		return locationFactory.apply(currentStage.getFile() + separator + DEFAULT_FILE_NAME);
	}

	@Override
	public IEscapyLocationContainer loadStage() {
		return loadStage(getDefault().getName());
	}

	private static IEscapyStage findDefault(IEscapyStage[] stages) {
		for (IEscapyStage stage : stages)
			if (stage.isDefault()) return  stage;
		throw new RuntimeException("Default stage REQUIRED!");
	}

}
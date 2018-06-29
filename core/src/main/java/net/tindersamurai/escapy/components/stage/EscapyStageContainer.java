package net.tindersamurai.escapy.components.stage;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.escapy.map.stage.IEscapyLocationContainer;
import net.tindersamurai.escapy.map.stage.IEscapyStage;
import net.tindersamurai.escapy.map.stage.IEscapyStageContainer;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Provide @Singleton
public class EscapyStageContainer implements IEscapyStageContainer {

	private final EscapyComponentParser componentParser;
	private final Map<String, IEscapyStage> stages;
	private final IEscapyStage defaultStage;

	private IEscapyStage currentStage;

	@Inject
	public EscapyStageContainer(EscapyComponentParser componentParser) {

		this.stages = new HashMap<>();

		for (IEscapyStage stage : stages)
			this.stages.put(stage.getName(), stage);
		this.defaultStage = findDefault(stages);
		this.componentParser = componentParser;
	}

	private static IEscapyStage[] init() {
		return null;
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
		// todo
		return null;
	}

	@Override
	public IEscapyLocationContainer loadStage() {
		// todo
		return null;
	}

	private static IEscapyStage findDefault(IEscapyStage[] stages) {
		for (IEscapyStage stage : stages)
			if (stage.isDefault()) return  stage;
		throw new RuntimeException("Default stage REQUIRED!");
	}

}
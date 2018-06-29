package net.tindersamurai.escapy.components.config;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.map.stage.IEscapyStage;

import javax.inject.Singleton;
import java.util.Map.Entry;

@Provide @Singleton
@EscapyComponentFactory("config")
public final class GameResourcesConfig {


	@EscapyComponent("main")
	public IEscapyStage[] main (
			@Arg("stages") Entry<String, String>[] stages,
			@Arg("default") String defaultStage
	) {
		IEscapyStage[] st = new IEscapyStage[stages.length];
		for (int i = 0; i < stages.length; i++)
			st[i] = IEscapyStage.DefaultImmutableStage(
					stages[i].getKey(),
					stages[i].getValue(),
					stages[i].getKey().equals(defaultStage)
			);
		return st;
	}


	@EscapyComponent("location")
	public void map() {


	}

}
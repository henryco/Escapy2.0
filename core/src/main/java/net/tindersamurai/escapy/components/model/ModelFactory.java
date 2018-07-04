package net.tindersamurai.escapy.components.model;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.model.plain.LayerModel;
import net.tindersamurai.escapy.map.model.IEscapyModel;

@Provide
@EscapyComponentFactory("model")
public class ModelFactory {


	@EscapyComponent("layer")
	public IEscapyModel layer(@Arg("args") IEscapyModel ... nested) {
		return new LayerModel(nested);
	}

}
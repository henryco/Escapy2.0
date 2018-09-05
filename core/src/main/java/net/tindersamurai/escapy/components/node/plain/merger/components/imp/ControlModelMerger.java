package net.tindersamurai.escapy.components.node.plain.merger.components.imp;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.control.plain.model.IModelListener;
import net.tindersamurai.escapy.components.node.plain.merger.components.IControlModelMerger;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.model.IEscapyModelDynamic;

@Provide @Log
public class ControlModelMerger implements IControlModelMerger {

	@Override
	public void mergeModelWithController(IEscapyModel model, IModelListener modelListener) {

		log.info(model + " : " + modelListener);
		if (modelListener == null) return;
		if (model instanceof IEscapyModelDynamic) {
			log.info("SETTING UP SPRITE PROVIDER");
			((IEscapyModelDynamic) model).setSpriteProvider(modelListener);
		}
	}
}
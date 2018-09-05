package net.tindersamurai.escapy.components.node.plain.merger.components;

import net.tindersamurai.escapy.components.control.plain.model.IModelListener;
import net.tindersamurai.escapy.map.model.IEscapyModel;

public interface IControlModelMerger {
	void mergeModelWithController(
			IEscapyModel model, IModelListener modelListener
	);
}
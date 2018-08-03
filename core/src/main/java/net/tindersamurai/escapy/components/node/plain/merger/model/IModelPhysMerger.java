package net.tindersamurai.escapy.components.node.plain.merger.model;

import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;

public interface IModelPhysMerger {
	void mergePhysWithModel(
			IEscapyPhysObject phys, IEscapyModel model
	);
}
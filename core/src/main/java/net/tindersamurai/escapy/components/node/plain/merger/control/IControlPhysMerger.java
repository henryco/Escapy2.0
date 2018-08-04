package net.tindersamurai.escapy.components.node.plain.merger.control;

import net.tindersamurai.escapy.control.IEscapyControllerListener;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;

public interface IControlPhysMerger {
	void mergePhysWithController(
			IEscapyControllerListener listener, IEscapyPhysObject phys
	);
}
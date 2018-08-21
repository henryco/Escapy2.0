package net.tindersamurai.escapy.components.node.plain.merger.components;

import net.tindersamurai.escapy.control.listener.IEscapyControllerListener;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;

public interface IControlPhysMerger {
	void mergePhysWithController(
			IEscapyControllerListener listener, IEscapyPhysObject phys
	);
}
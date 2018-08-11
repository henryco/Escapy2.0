package net.tindersamurai.escapy.components.node.plain.merger.components.imp;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.node.plain.merger.components.IControlPhysMerger;
import net.tindersamurai.escapy.control.listener.IEscapyControllerListener;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;

@Provide @Log
public class ControlPhysMerger implements IControlPhysMerger {

	@Override
	public void mergePhysWithController(
			IEscapyControllerListener listener,
			IEscapyPhysObject phys
	) {
		if (phys == null || listener == null) return;
		listener.addUserData(phys);
	}
}
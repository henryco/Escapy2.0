package net.tindersamurai.escapy.components.control.plain.keyboard;

import net.tindersamurai.escapy.components.control.plain.CoreCharacterListener;
import net.tindersamurai.escapy.physics.obj.EscapyPhysObject;


public class KbPhysBodyListener extends
		CoreCharacterListener<EscapyPhysObject> {

	@Override
	protected Class<EscapyPhysObject> getDataType() {
		return EscapyPhysObject.class;
	}

	@Override
	public void onInteract() {

	}

	@Override
	public void onMoveLeft() {

	}

	@Override
	public void onMoveRight() {

	}

	@Override
	public void onRun() {

	}

	@Override
	public void onSit() {

	}
}
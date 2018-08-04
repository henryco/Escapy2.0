package net.tindersamurai.escapy.components.control.plain.keyboard;

import lombok.NoArgsConstructor;
import net.tindersamurai.escapy.components.control.plain.CoreCharacterListener;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;

@NoArgsConstructor
public class KbPhysObjectListener extends
		CoreCharacterListener<IEscapyPhysObject> {

	@Override
	protected Class<IEscapyPhysObject> getDataType() {
		return IEscapyPhysObject.class;
	}

	@Override
	public void onInteract() {
		System.out.println("interact");
	}

	@Override
	public void onMoveLeft() {
		System.out.println("left");
	}

	@Override
	public void onMoveRight() {
		System.out.println("right");
	}

	@Override
	public void onRun() {
		System.out.println("run");
	}

	@Override
	public void onSit() {
		System.out.println("sit");
	}
}
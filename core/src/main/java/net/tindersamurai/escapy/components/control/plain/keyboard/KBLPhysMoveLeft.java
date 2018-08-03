package net.tindersamurai.escapy.components.control.plain.keyboard;


import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.components.control.plain.AbstractControllerListener;
import net.tindersamurai.escapy.control.IEscapyControllerListener;
import net.tindersamurai.escapy.physics.obj.EscapyPhysObject;

@Log
public class KBLPhysMoveLeft extends AbstractControllerListener<EscapyPhysObject>
		implements IEscapyControllerListener.MoveLeftListener {


	@Override
	public void onMoveLeft() {
		final EscapyPhysObject[] objects = getUserData();
		for (val object : objects) {
			val body = object.getFixture().getBody();
		}
	}

	@Override
	protected Class<EscapyPhysObject> getDataType() {
		return EscapyPhysObject.class;
	}

}
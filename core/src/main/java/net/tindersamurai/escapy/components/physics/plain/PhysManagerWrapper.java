package net.tindersamurai.escapy.components.physics.plain;

import com.badlogic.gdx.physics.box2d.Fixture;
import lombok.Getter;
import lombok.Setter;
import net.tindersamurai.escapy.physics.IEscapyPhysics;
import net.tindersamurai.escapy.physics.event.IEscapyPhysListener;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;

public class PhysManagerWrapper implements IEscapyPhysObject {

	private @Getter @Setter IEscapyPhysics physicsManager;
	private @Getter final String id;

	public PhysManagerWrapper(String id) {
		this.id = id;
	}

	@Override
	public void setPhysListener(IEscapyPhysListener listener) {
		// NOTHING
	}

	@Override
	public Fixture getMainFixture() {
		return null;
	}

	@Override
	public void dispose() {
		physicsManager.dispose();
	}
}

package net.tindersamurai.escapy.physics.obj;

import com.badlogic.gdx.physics.box2d.Fixture;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.physics.IEscapyPhysics;
import net.tindersamurai.escapy.physics.event.IEscapyPhysListener;

@Log
public class EscapyPhysObject implements IEscapyPhysObject {

	private @Getter @Setter IEscapyPhysics physicsManager;
	private @Getter final Fixture fixture;
	private @Getter final String id;

	public EscapyPhysObject(String id, Fixture fixture) {
		if (fixture == null)
			throw new RuntimeException("Fixture cannot be NULL");
		this.fixture = fixture;
		this.id = id;
	}

	@Override
	public void setPhysListener(IEscapyPhysListener listener) {
		log.info("Listener: " + listener);
		IEscapyPhysListener[] data = (IEscapyPhysListener[]) fixture.getUserData();
		if (data == null) data = new IEscapyPhysListener[0];

		IEscapyPhysListener[] listeners = new IEscapyPhysListener[data.length + 1];
		System.arraycopy(data, 0, listeners, 0, data.length);
		listeners[listeners.length - 1] = listener;

		fixture.setUserData(listeners);
	}

	@Override
	public void dispose() {
		log.info("PHYS DISPOSE");
		fixture.setUserData(null);
	}
}
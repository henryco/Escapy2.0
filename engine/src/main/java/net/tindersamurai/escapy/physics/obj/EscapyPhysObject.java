package net.tindersamurai.escapy.physics.obj;

import com.badlogic.gdx.physics.box2d.Fixture;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.physics.IEscapyPhysics;
import net.tindersamurai.escapy.physics.event.IEscapyPhysListener;

import java.util.Arrays;
import java.util.HashSet;

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

		fixture.setUserData(
				new HashSet<IEscapyPhysListener>(Arrays.asList(data)) {{
					add(listener);
				}}.toArray(new IEscapyPhysListener[0])
		);
	}

	@Override
	public void dispose() {
		log.info("PHYS DISPOSE");
		fixture.setUserData(null);
	}
}
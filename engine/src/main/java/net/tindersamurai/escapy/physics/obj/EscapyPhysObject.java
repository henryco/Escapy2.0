package net.tindersamurai.escapy.physics.obj;

import com.badlogic.gdx.physics.box2d.Fixture;
import lombok.Getter;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.physics.event.IEscapyPhysListener;

@Log
public class EscapyPhysObject implements IEscapyPhysObject {

	private @Getter final Fixture fixture;

	public EscapyPhysObject(Fixture fixture) {
		this.fixture = fixture;
	}

	@Override
	public void setPhysListener(IEscapyPhysListener listener) {
		log.info("Listener: " + listener);
		fixture.setUserData(listener);
	}

	@Override
	public void dispose() {
		log.info("PHYS DISPOSE");
		// todo
	}
}
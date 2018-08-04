package net.tindersamurai.escapy.physics.obj;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import lombok.Getter;
import lombok.extern.java.Log;
import net.dermetfan.gdx.physics.box2d.ContactAdapter;
import net.tindersamurai.escapy.physics.IEscapyPhysics;
import net.tindersamurai.escapy.physics.event.IEscapyPhysListener;

import java.util.Arrays;
import java.util.HashSet;

@Log
public class EscapyPhysObject implements IEscapyPhysObject {

	private @Getter IEscapyPhysics physicsManager;
	private @Getter final Fixture mainFixture;
	private @Getter final String id;

	private @Getter boolean grounded;

	private final ContactListener contactListener = new ContactAdapter() {
		@Override
		public void beginContact(Contact contact) {
			grounded = true;
		}
		@Override
		public void endContact(Contact contact) {
			grounded = false;
		}
	};

	public EscapyPhysObject(String id, Fixture fixture) {
		if (fixture == null)
			throw new RuntimeException("Fixture cannot be NULL");
		this.mainFixture = fixture;
		this.id = id;
	}

	@Override
	public void setPhysListener(IEscapyPhysListener listener) {
		log.info("Listener: " + listener);
		IEscapyPhysListener[] data = (IEscapyPhysListener[]) mainFixture.getUserData();
		if (data == null) data = new IEscapyPhysListener[0];

		mainFixture.setUserData(
				new HashSet<IEscapyPhysListener>(Arrays.asList(data)) {{
					add(listener);
				}}.toArray(new IEscapyPhysListener[0])
		);
	}

	@Override
	public void dispose() {
		log.info("PHYS DISPOSE");
		mainFixture.setUserData(null);
		resetListener();
	}

	@Override
	public void setPhysicsManager(IEscapyPhysics physicsManager) {
		resetListener();
		this.physicsManager = physicsManager;
		this.physicsManager.addContactListener(contactListener);
	}

	private void resetListener() {
		if (this.physicsManager != null)
			this.physicsManager.removeContactListener(contactListener);
	}
}
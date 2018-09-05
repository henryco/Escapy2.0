package net.tindersamurai.escapy.physics.obj;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import lombok.Getter;
import lombok.extern.java.Log;
import lombok.val;
import net.dermetfan.gdx.physics.box2d.ContactAdapter;
import net.tindersamurai.escapy.physics.IEscapyPhysics;
import net.tindersamurai.escapy.physics.event.IEscapyPhysListener;

@Log
public class EscapyPhysAliveObject implements IEscapyPhysAliveObject {

	private @Getter IEscapyPhysics physicsManager;
	private @Getter Fixture mainFixture;
	private @Getter final String id;

	private @Getter boolean grounded;


	public EscapyPhysAliveObject(String id) {
		this.id = id;
	}

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

	@Override
	public void setPhysListener(IEscapyPhysListener listener) {

	}

	@Override
	public void setPhysicsManager(IEscapyPhysics physicsManager) {
		resetListener();
		this.physicsManager = physicsManager;
		this.physicsManager.addContactListener(contactListener);
	}


	@Override
	public void dispose() {
		mainFixture.setUserData(null);
		resetListener();
		val body = mainFixture.getBody();
		val world = body.getWorld();
		world.destroyBody(body);
	}

	private void resetListener() {
		if (this.physicsManager != null)
			this.physicsManager.removeContactListener(contactListener);
	}
}
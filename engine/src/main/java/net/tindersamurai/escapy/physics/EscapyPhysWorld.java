package net.tindersamurai.escapy.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.LongMap;
import lombok.Getter;
import lombok.extern.java.Log;
import lombok.val;
import net.dermetfan.gdx.physics.box2d.ContactMultiplexer;
import net.tindersamurai.escapy.physics.event.IEscapyPhysListener;

import java.lang.reflect.Field;

@Log
public class EscapyPhysWorld implements IEscapyPhysics {

	private @Getter final float pixelScale;
	private @Getter final float timeStep;
	private @Getter final World world;

	private final ContactMultiplexer contactMultiplexer;
	private final LongMap<Fixture> fixtures;

	public EscapyPhysWorld (
			Vector2 gravity,
			float pixelScale,
			float timeStep
	) {
		log.info("NEW PHYS WORLD INSTANCE");

		this.contactMultiplexer = new ContactMultiplexer();

		this.world = new World(gravity, true); {
			world.setContactListener(contactMultiplexer);
		}

		this.timeStep = timeStep;
		this.pixelScale = pixelScale;
		this.fixtures = accessFixtures();
	}


	private float accumulator = 0;

	@Override
	public void updateWorld(float delta) {
		// fixed time step
		// max frame time to avoid spiral of death (on slow devices)
		float frameTime = Math.min(delta, 0.25f);
		accumulator += frameTime;
		while (accumulator >= timeStep) {
			world.step(timeStep, 6, 2);
			accumulator -= timeStep;

//			Gdx.app.postRunnable(() -> {
				for (val fixture : fixtures) {
					val data = fixture.value.getUserData();
					if (data instanceof IEscapyPhysListener[]) {
						val listeners = (IEscapyPhysListener[]) data;
						val body = fixture.value.getBody();
						val position = body.getPosition();
						for (val l : listeners) {
							l.onPhysPositionUpdate(position.x, position.y);
							l.onPhysAngleUpdate(body.getAngle());
						}
					}
				}
//			});
		}
	}

	@Override
	public void dispose() {
		if (contactMultiplexer != null) contactMultiplexer.clear();
		if (world != null) world.dispose();
	}


	private LongMap<Fixture> accessFixtures() {
		try {
			final Field field = World.class.getDeclaredField("fixtures");
			field.setAccessible(true);
			//noinspection unchecked
			return  (LongMap<Fixture>) field.get(world);
		} catch (Exception e) {
			log.throwing(this.getClass().getName(), "accessFixtures", e);
			throw new RuntimeException("Cannot access fixtures from " + World.class.getName() + " instance");
		}
	}

	@Override
	public void addContactListener(ContactListener listener) {
		if (listener == null) {
			log.warning("Cannot add ContactListener cause NULL");
			return;
		}
		contactMultiplexer.add(listener);
	}

	@Override
	public void removeContactListener(ContactListener listener) {
		if (listener == null) {
			log.warning("Cannot remove ContactListener, cause NULL");
			return;
		}
		contactMultiplexer.remove(listener);
	}


}
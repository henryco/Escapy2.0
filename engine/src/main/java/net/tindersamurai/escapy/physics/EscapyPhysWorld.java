package net.tindersamurai.escapy.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.LongMap;
import lombok.Getter;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.physics.event.IEscapyPhysListener;

import java.lang.reflect.Field;

@Log
public class EscapyPhysWorld implements IEscapyPhysics {

	private @Getter final float timeStep;
	private @Getter final World world;
	private final LongMap<Fixture> fixtures;

	public EscapyPhysWorld (
			Vector2 gravity,
			float timeStep
	) {
		log.info("NEW PHYS WORLD INSTANCE");
		this.world = new World(gravity, true);
		this.timeStep = timeStep;

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

			for (val fixture : fixtures) {
				val data = fixture.value.getUserData();
				if (data instanceof IEscapyPhysListener) {
					val l = (IEscapyPhysListener) data;
					val body = fixture.value.getBody();
					val position = body.getPosition();
					l.onPhysPositionUpdate(position.x, position.y);
					l.onPhysAngleUpdate(body.getAngle());
				}
			}
		}
	}

	@Override
	public void dispose() {
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
}
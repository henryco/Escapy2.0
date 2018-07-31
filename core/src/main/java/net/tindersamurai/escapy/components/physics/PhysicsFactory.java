package net.tindersamurai.escapy.components.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.physics.plain.PhysManagerWrapper;
import net.tindersamurai.escapy.physics.EscapyPhysWorld;
import net.tindersamurai.escapy.physics.IEscapyPhysics;
import net.tindersamurai.escapy.physics.obj.EscapyPhysObject;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@Provide
@EscapyComponentFactory("physics")
public class PhysicsFactory {

	private static final String MANAGER_ID = "manager";

	private final Map<String, IEscapyPhysObject> objectMap = new HashMap<>();
	private IEscapyPhysics physics;

	@SafeVarargs @EscapyComponent("manager")
	public final IEscapyPhysObject physicsManager(
			@Arg("gravity") Float[] gravity,
			@Arg("fps") Float fps,
			@Arg("objects") Function<IEscapyPhysics, IEscapyPhysObject>... objects
	) {
		objectMap.clear();

		if (physics != null)
			physics.dispose();

		physics = new EscapyPhysWorld (
				gravity == null ? new Vector2(0, -9.8f) : new Vector2(gravity[0], gravity[1]),
				fps == null ? 1f / 60f : 1f / fps
		);
		val object = new PhysManagerWrapper(MANAGER_ID);
		object.setPhysicsManager(physics);
		objectMap.put(MANAGER_ID, object);

		for (val def : objects) {
			val o = def.apply(physics);
			o.setPhysicsManager(physics);
			if (o.getId() != null)
				objectMap.put(o.getId(), o);
		}

		return object;
	}


	@EscapyComponentFactory("shape")
	public final class _Shape {

		@EscapyComponent("polygon")
		public final Supplier<Shape> polygon (
				@Arg("vertices") Float[] vertices,
				@Arg("radius") Float radius
		) {
			return () -> new PolygonShape() {{
				if (vertices != null) {
					float[] vert = new float[vertices.length];
					for (int i = 0; i < vert.length; i++)
						vert[i] = vertices[i];
					set(vert);
				}
				if (radius != null) setRadius(radius);
			}};
		}

		@EscapyComponent("box")
		public final Supplier<Shape> box (
				@Arg("size") Float[] dim,
				@Arg("radius") Float radius
		) {
			return () -> new PolygonShape() {{
				if (dim != null) setAsBox(dim[0] * 0.5f, dim[1] * 0.5f);
				if (radius != null) setRadius(radius);
			}};
		}

		@EscapyComponent("circle")
		public final Supplier<Shape> circle (
				@Arg("position") Float[] pos,
				@Arg("radius") Float radius
		) {
			return () -> new CircleShape() {{
				if (pos != null) setPosition(new Vector2(pos[0], pos[1]));
				if (radius != null) setRadius(radius);
			}};
		}

		@EscapyComponent("chain")
		public final Supplier<Shape> chain (
				@Arg("vertices") Float[] vertices,
				@Arg("radius") Float radius
		) {
			return () -> new ChainShape() {{
				if (vertices != null) {
					float[] vert = new float[vertices.length];
					for (int i = 0; i < vert.length; i++)
						vert[i] = vertices[i];
					createChain(vert);
				}
				if (radius != null) setRadius(radius);
			}};
		}

		@EscapyComponent("edge")
		public final Supplier<Shape> edge (
				@Arg("vertices") Float[] vert,
				@Arg("radius") Float radius
		) {
			return () -> new EdgeShape() {{
				if (vert != null) set(vert[0], vert[1], vert[2], vert[3]);
				if (radius != null) setRadius(radius);
			}};
		}
	}


	@EscapyComponentFactory("object")
	public final class Object {

		@EscapyComponent("get")
		public IEscapyPhysObject get(@Arg("id") String id) {
			return objectMap.get(id);
		}

		@EscapyComponent("def")
		public Function<IEscapyPhysics, IEscapyPhysObject> objectDef (
				@Arg("id") String id,
				// StaticBody(0), KinematicBody(1), DynamicBody(2);
				@Arg("type") String type,
				@Arg("position") Float[] pos,
				@Arg("angle") Float angle,
				@Arg("rotation") Boolean rotation,
				@Arg("linearDamping") Float lDamp,
				@Arg("angularDamping") Float aDamp,
				@Arg("bullet") Boolean bullet,
				@Arg("gravityScale") Float gScale,
				@Arg("linearVelocity") Float[] lVel,
				@Arg("angularVelocity") Float aVel,
				@Arg("density") Float density,
				@Arg("restitution") Float restitution,
				@Arg("friction") Float friction,
				@Arg("sensor") Boolean sensor,
				@Arg("shape") Supplier<Shape> shape
		) {
			return physics -> {
				val world = physics.getWorld();

				val bodyDef = new BodyDef(); {
					bodyDef.type = BodyDef.BodyType.valueOf(type);

					if (lVel != null) bodyDef.linearVelocity.set(lVel[0], lVel[1]);
					if (pos != null) bodyDef.position.set(pos[0], pos[1]);
					if (gScale != null) bodyDef.gravityScale = gScale;
					if (aDamp != null) bodyDef.angularDamping = aDamp;
					if (aVel != null) bodyDef.angularVelocity = aVel;
					if (rotation != null) bodyDef.fixedRotation = !rotation;
					if (lDamp != null) bodyDef.linearDamping = lDamp;
					if (bullet != null) bodyDef.bullet = bullet;
					if (angle != null) bodyDef.angle = angle;
				}

				val body = world.createBody(bodyDef);
				val _shape = shape.get();

				val fixtureDef = new FixtureDef(); {
					fixtureDef.shape = _shape;
					if (restitution != null) fixtureDef.restitution = restitution;
					if (density != null) fixtureDef.density = density;
					if (friction != null) fixtureDef.friction = friction;
					if (sensor != null) fixtureDef.isSensor = sensor;
				}

				val fixture = body.createFixture(fixtureDef); {
					_shape.dispose();
				}

				return new EscapyPhysObject(id,fixture);
			};
		}

		@EscapyComponent("new")
		public IEscapyPhysObject objectNew(
				@Arg("id") String id,
				@Arg("type") String type,
				@Arg("position") Float[] pos,
				@Arg("angle") Float angle,
				@Arg("rotation") Boolean rotation,
				@Arg("linearDamping") Float lDamp,
				@Arg("angularDamping") Float aDamp,
				@Arg("bullet") Boolean bullet,
				@Arg("gravityScale") Float gScale,
				@Arg("linearVelocity") Float[] lVel,
				@Arg("angularVelocity") Float aVel,
				@Arg("density") Float dens,
				@Arg("restitution") Float rest,
				@Arg("friction") Float friction,
				@Arg("sensor") Boolean sensor,
				@Arg("shape") Supplier<Shape> shape
		) {
			val o = objectDef (
					id, type, pos, angle, rotation, lDamp,
					aDamp, bullet, gScale, lVel, aVel,
					dens, rest, friction, sensor, shape
			).apply(physics);
			o.setPhysicsManager(physics);
			if (o.getId() != null)
				objectMap.put(o.getId(), o);
			return o;
		}

	}




}
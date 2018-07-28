package net.tindersamurai.escapy.components.physics;

import com.badlogic.gdx.math.Vector2;
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

@Provide
@EscapyComponentFactory("physics")
public class PhysicsFactory {

	private IEscapyPhysics physics;

	@EscapyComponent("manager")
	public IEscapyPhysObject physicsManager (
			@Arg("gravity") Float[] gravity,
			@Arg("fps") Float fps
	) {
		if (physics != null)
			physics.dispose();

		physics = new EscapyPhysWorld(
				gravity == null ? new Vector2(0, -9.8f) : new Vector2(gravity[0], gravity[1]),
				fps == null ? 1f / 60f : 1f / fps
		);
		val object = new PhysManagerWrapper();
		object.setPhysicsManager(physics);
		return object;
	}


	@EscapyComponent("object")
	public IEscapyPhysObject objectByName (
			// todo FIXTURE DATA
	) {
		val object = new EscapyPhysObject(null);
		object.setPhysicsManager(physics);
		return object;
	}


}
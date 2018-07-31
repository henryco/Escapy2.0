package net.tindersamurai.escapy.components.physics;

import com.badlogic.gdx.math.Vector2;
import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.config.ConfigModule;
import net.tindersamurai.escapy.physics.EscapyPhysWorld;
import net.tindersamurai.escapy.physics.IEscapyPhysics;

import java.util.function.Function;

@Module(componentsRootClass = PhysicsModule.class,
		include = { ConfigModule.class }
) @Log public final class PhysicsModule {

	@Provide public final Function<Void, IEscapyPhysics> physicsFactory () {
		return v -> new EscapyPhysWorld(new Vector2(0, -9.8f), 1/60f);
	}

}
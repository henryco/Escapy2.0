package net.tindersamurai.escapy.components.control;
import com.github.henryco.injector.meta.annotations.Module;
import net.tindersamurai.escapy.components.config.ConfigModule;

@Module(
		componentsRootClass = ControlModule.class,
		include = { ConfigModule.class }
) public final class ControlModule {

}
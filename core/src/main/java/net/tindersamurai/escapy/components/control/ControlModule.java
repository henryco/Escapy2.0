package net.tindersamurai.escapy.components.control;
import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.components.config.ConfigModule;
import net.tindersamurai.escapy.control.manager.EscapyControlManager;
import net.tindersamurai.escapy.control.manager.IEscapyControlManager;

import javax.inject.Singleton;

@Module(
		componentsRootClass = ControlModule.class,
		include = { ConfigModule.class }
) public final class ControlModule {

	@Provide @Singleton
	public final IEscapyControlManager provideControlManager() {
		return EscapyControlManager.getInstance();
	}

}
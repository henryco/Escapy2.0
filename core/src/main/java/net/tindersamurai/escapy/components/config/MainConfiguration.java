package net.tindersamurai.escapy.components.config;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContextConfiguration;
import net.tindersamurai.escapy.context.game.configuration.util.PropertyKeysStorage;

import javax.inject.Singleton;

import static java.io.File.separator;

@Provide @Singleton
public final class MainConfiguration extends EscapyGameContextConfiguration {

	@Override
	public String getWorkDir() {
		return super.getWorkDir().replace(separator+"core"+separator+"assets", "") + separator;
	}

	@Override
	public String getResourcesDir() {
		return getWorkDir() + "res" + separator;
	}

	@Override
	public String getConfigsFilePath() {
		return this.getResourcesDir() + "config" + separator;
	}

	@Override
	public void configurePropertyKeys(PropertyKeysStorage propertyKeyStorage) {
		propertyKeyStorage
				.addPropertyKey("BLEND_SHADERS_ROOT_DIR_PATH")
				.addPropertyValue(getResourcesDir() + separator + "shaders" + separator + "blend" + separator)
		.save();
	}

}
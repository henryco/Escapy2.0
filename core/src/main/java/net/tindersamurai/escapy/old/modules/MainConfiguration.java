package net.tindersamurai.escapy.old.modules;

import net.tindersamurai.escapy.context.game.configuration.EscapyGameContextConfiguration;
import net.tindersamurai.escapy.context.game.configuration.util.PropertyKeysStorage;

import static java.io.File.separator;

@Deprecated
public class MainConfiguration extends EscapyGameContextConfiguration {

	@Override
	public String getResourcesDir() {
		String local = System.getProperty("user.dir").replace(separator+"core"+separator+"assets", "");
		return local + separator + "res";
	}

	@Override
	public String getConfigsFilePath() {
		return this.getResourcesDir() + separator + "configurations";
	}

	@Override
	public void configurePropertyKeys(PropertyKeysStorage propertyKeyStorage) {
		propertyKeyStorage
				.addPropertyKey("BLEND_SHADERS_ROOT_DIR_PATH")
				.addPropertyValue(getResourcesDir() + separator + "shaders" + separator + "blend")
		.save();
	}

}
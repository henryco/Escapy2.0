package net.irregular.escapy.environment;

import net.irregular.escapy.engine.env.context.game.configuration.EscapyGameContextConfiguration;
import net.irregular.escapy.engine.env.context.game.configuration.util.PropertyKeysStorage;

import static java.io.File.separator;

/**
 * @author Henry on 21/07/17.
 */
public class MainConfiguration extends EscapyGameContextConfiguration {



	@Override
	public String getConfigsFilePath() {
		return this.getResourcesDir() + separator + "configurations";
	}



	@Override
	public String getResourcesDir() {
		String local = System.getProperty("user.dir").replace(separator+"core"+separator+"assets", "");
		return local + separator + "res";
	}



	@Override
	public void configurePropertyKeys(PropertyKeysStorage propertyKeyStorage) {

		propertyKeyStorage
				.addPropertyKey("BLEND_SHADERS_ROOT_DIR_PATH")
				.addPropertyValue(getResourcesDir() + separator + "shaders" + separator + "blend")
		.save();
	}



}
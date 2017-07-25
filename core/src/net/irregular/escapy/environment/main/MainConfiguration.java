package net.irregular.escapy.environment.main;

import net.irregular.escapy.engine.env.context.game.configuration.EscapyGameContextConfiguration;
import net.irregular.escapy.engine.env.context.game.configuration.util.PropertyKeysContainer;

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
	public void configurePropertyKeys(PropertyKeysContainer propertyKeysContainer) {

		propertyKeysContainer

				.addPropertyKey("DYNAMIC_LIGHT_SHADER_FILE_PATH")
				.addPropertyValue(getResourcesDir()
						+separator+"shaders"+separator +"light"+separator+"volume"+separator+"dynamicLights"
				)

				.and()
				.addPropertyKey("LIGHT_SOURCE_SHADER_FILE_PATH")
				.addPropertyValue(getResourcesDir()
						+ separator + "shaders" + separator + "light" + separator + "source" + separator + "lightSrc"
				)

				.and()
				.addPropertyKey("BLEND_SHADERS_ROOT_DIR_PATH")
				.addPropertyValue(getResourcesDir() + separator + "shaders" + separator + "blend")

		.save();
	}



}
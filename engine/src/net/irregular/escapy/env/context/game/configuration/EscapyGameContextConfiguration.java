package net.irregular.escapy.env.context.game.configuration;

import net.irregular.escapy.env.context.annotation.EscapyAPI;
import net.irregular.escapy.env.context.annotation.meta.AnnotationProcessor;
import net.irregular.escapy.env.context.game.configuration.util.PropertyKeysStorage;

/**
 * @author Henry on 28/06/17.
 */
@EscapyAPI // TODO: 30/06/17 MORE CONFIGURATIONS
public abstract class EscapyGameContextConfiguration {


	public AnnotationProcessor getAnnotationProcessor() {
		return new AnnotationProcessor();
	}

	public String getConfigsFilePath() {
		return System.getProperty("user.dir");
	}

	public String getWorkDir() {
		return System.getProperty("user.dir");
	}

	public String getResourcesDir() {
		return System.getProperty("user.dir");
	}

	public void configurePropertyKeys(PropertyKeysStorage propertyKeysContainer) {}


}

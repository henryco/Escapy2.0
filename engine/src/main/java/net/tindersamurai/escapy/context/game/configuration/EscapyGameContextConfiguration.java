package net.tindersamurai.escapy.context.game.configuration;

import net.tindersamurai.escapy.context.annotation.meta.AnnotationProcessor;
import net.tindersamurai.escapy.context.game.configuration.util.PropertyKeysStorage;

/**
 * @author Henry on 28/06/17.
 */
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

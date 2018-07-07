package net.tindersamurai.escapy.context.game.configuration;

import net.tindersamurai.escapy.context.annotation.meta.AnnotationProcessor;
import net.tindersamurai.escapy.context.game.configuration.util.PropertyKeysStorage;

/**
 * @author Henry on 28/06/17.
 */
public abstract class EscapyGameContextConfiguration implements EscapyGameContext {


	public AnnotationProcessor getAnnotationProcessor() {
		return new AnnotationProcessor();
	}

	@Override public String getConfigsFilePath() {
		return System.getProperty("user.dir");
	}

	@Override public int getDefaultScrWidth() {
		return 1280;
	}

	@Override public int getDefaultScrHeight() {
		return 720;
	}

	@Override public String getWorkDir() {
		return System.getProperty("user.dir");
	}

	@Override public String getResourcesDir() {
		return System.getProperty("user.dir");
	}

	public void configurePropertyKeys(PropertyKeysStorage propertyKeysContainer) {}


}

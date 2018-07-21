package net.tindersamurai.escapy.context.game.configuration;

import net.tindersamurai.escapy.context.game.configuration.util.PropertyKeysStorage;

public interface EscapyGameContext {

	String getConfigsFilePath();

	int getDefaultScrWidth();

	int getDefaultScrHeight();

	String getWorkDir();

	String getResourcesDir();

	default void configurePropertyKeys(PropertyKeysStorage propertyKeysContainer) {}

	@SuppressWarnings("unchecked")
	<T> T getProperty(String name);
}
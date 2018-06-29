package net.tindersamurai.escapy.context.game;

import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContextConfiguration;
import net.tindersamurai.escapy.context.game.configuration.util.DefaultPropertyKeysContainer;
import net.tindersamurai.escapy.context.game.configuration.util.PropertyKeysStorage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Henry on 21/07/17.
 */
public final class Escapy {

	private static Escapy ourInstance = new Escapy();
	public static Escapy getInstance() {
		return ourInstance;
	}


	private final PropertyKeysStorage propertyKeysContainer;
	private final Map<String, Object> configValuesMap;

	private Escapy() {
		this.configValuesMap = new HashMap<>();
		this.propertyKeysContainer = new DefaultPropertyKeysContainer(configValuesMap);
	}




	private EscapyGameContext contextConfiguration = null;
	public void setContextConfiguration(EscapyGameContextConfiguration contextConfiguration) {
		if (this.contextConfiguration != null) throw new RuntimeException("Context configuration already exists");

		this.contextConfiguration = contextConfiguration;
		contextConfiguration.configurePropertyKeys(propertyKeysContainer);
	}

	public static EscapyGameContext getGameContext() {
		return ourInstance.contextConfiguration;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getProperty(String propertyKey) {
		return (T) ourInstance.configValuesMap.get(propertyKey);
	}



}

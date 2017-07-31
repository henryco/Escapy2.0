package net.irregular.escapy.environment.context.game;

import net.irregular.escapy.environment.context.game.configuration.EscapyGameContextConfiguration;
import net.irregular.escapy.environment.context.game.configuration.util.DefaultPropertyKeysContainer;
import net.irregular.escapy.environment.context.game.configuration.util.PropertyKeysStorage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Henry on 21/07/17.
 */
public class Escapy {

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




	private EscapyGameContextConfiguration contextConfiguration = null;
	public void setContextConfiguration(EscapyGameContextConfiguration contextConfiguration) {
		if (this.contextConfiguration != null) throw new RuntimeException("Context configuration already exists");

		this.contextConfiguration = contextConfiguration;
		contextConfiguration.configurePropertyKeys(propertyKeysContainer);
	}



	public static String getConfigsFilePath() {
		return ourInstance.contextConfiguration.getConfigsFilePath();
	}

	public static String getWorkDir() {
		return ourInstance.contextConfiguration.getWorkDir();
	}

	public static String getResourcesDir() {
		return ourInstance.contextConfiguration.getResourcesDir();
	}


	@SuppressWarnings("unchecked")
	public static <T> T getProperty(String propertyKey) {
		return (T) ourInstance.configValuesMap.get(propertyKey);
	}



}

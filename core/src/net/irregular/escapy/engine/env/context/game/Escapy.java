package net.irregular.escapy.engine.env.context.game;

import net.irregular.escapy.engine.env.context.game.configuration.EscapyGameContextConfiguration;

/**
 * @author Henry on 21/07/17.
 */
public class Escapy {

	private static Escapy ourInstance = new Escapy();
	public static Escapy getInstance() {
		return ourInstance;
	}

	private Escapy() {}

	private EscapyGameContextConfiguration contextConfiguration;
	public void setContextConfiguration(EscapyGameContextConfiguration contextConfiguration) {
		this.contextConfiguration = contextConfiguration;
	}


	public static String getConfigsFilePath() {
		return ourInstance.contextConfiguration.getConfigsFilePath();
	}

	public static String getWorkDir() {
		return ourInstance.contextConfiguration.getWorkDir();
	}
}

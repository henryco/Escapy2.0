package net.irregular.escapy.environment.main;

import net.irregular.escapy.engine.env.context.game.configuration.EscapyGameContextConfiguration;

import static java.io.File.separator;

/**
 * @author Henry on 21/07/17.
 */
public class MainConfiguration extends EscapyGameContextConfiguration {

	@Override
	public String getConfigsFilePath() {
		return System.getProperty("user.dir") + separator + "res" + separator + "configurations";
	}
}

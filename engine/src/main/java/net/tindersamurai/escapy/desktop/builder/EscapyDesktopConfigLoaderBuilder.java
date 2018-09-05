package net.tindersamurai.escapy.desktop.builder;

import net.tindersamurai.escapy.desktop.EscapyDesktopConfigLoader;

/**
 * @author Henry on 31/07/17.
 */
public interface EscapyDesktopConfigLoaderBuilder {

	EscapyDesktopConfigLoader build();

	static DefaultDesktopConfigLoaderBuilder Default() {
		return new DefaultDesktopConfigLoaderBuilder();
	}
}

package net.irregular.escapy.desktop.config.builder;

import net.irregular.escapy.desktop.config.DesktopConfigLoader;

/**
 * @author Henry on 31/07/17.
 */
public interface DesktopConfigLoaderBuilder {

	DesktopConfigLoader build();

	static DefaultDesktopConfigLoaderBuilder Default() {
		return new DefaultDesktopConfigLoaderBuilder();
	}
}

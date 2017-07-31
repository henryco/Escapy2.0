package net.irregular.escapy.desktop.builder;

import net.irregular.escapy.desktop.DesktopConfigLoader;

/**
 * @author Henry on 31/07/17.
 */
public interface DesktopConfigLoaderBuilder {

	DesktopConfigLoader build();

	static DefaultDesktopConfigLoaderBuilder Default() {
		return new DefaultDesktopConfigLoaderBuilder();
	}
}

package net.irregular.escapy.desktop.config;


/**
 * @author Henry on 15/07/17.
 */
public interface DesktopConfigLoader {

	<T> T loadDesktopConfig();
}

package net.irregular.escapy.environment.context.annotation.meta.name;

import net.irregular.escapy.environment.context.screen.EscapyScreen;

import java.util.Map;

/**
 * @author Henry on 28/06/17.
 */
public interface ScreenMapProcessorExecutor {

	Map<String, EscapyScreen> processScreenMap(final EscapyScreen screen,
											   final Map<String, EscapyScreen> map
	);
}

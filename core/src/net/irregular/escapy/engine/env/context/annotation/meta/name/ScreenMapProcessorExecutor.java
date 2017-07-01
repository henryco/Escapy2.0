package net.irregular.escapy.engine.env.context.annotation.meta.name;

import net.irregular.escapy.engine.env.context.screen.EscapyScreen;

import java.util.Map;

/**
 * @author Henry on 28/06/17.
 */
public interface ScreenMapProcessorExecutor {

	Map<String, EscapyScreen> processScreenMap(final EscapyScreen screen,
											   final Map<String, EscapyScreen> map
	);
}

package net.irregular.escapy.environment.context.annotation.meta.name;

import net.irregular.escapy.environment.context.annotation.ScreenName;
import net.irregular.escapy.environment.context.screen.EscapyScreen;

import java.util.Map;

/**
 * @author Henry on 28/06/17.
 */
public class ScreenMapProcessorExecutorImp implements ScreenMapProcessorExecutor {

	@Override
	public Map<String, EscapyScreen> processScreenMap(
			final EscapyScreen screen,
			final Map<String, EscapyScreen> map
	) {
		final ScreenName screenNameAnnotation = screen.getClass().getAnnotation(ScreenName.class);
		if (screenNameAnnotation == null) return map;
		map.put(screenNameAnnotation.value(), screen);
		return map;
	}
}

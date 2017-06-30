package net.irregular.escapy.engine.env.context.annotation.meta.name;

import net.irregular.escapy.engine.env.context.screen.EscapyScreen;
import net.irregular.escapy.engine.env.context.annotation.ScreenName;

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

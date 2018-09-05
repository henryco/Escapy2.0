package net.tindersamurai.escapy.context.annotation.meta;


import net.tindersamurai.escapy.context.annotation.meta.name.ScreenMapProcessor;
import net.tindersamurai.escapy.context.annotation.meta.name.ScreenMapProcessorExecutor;
import net.tindersamurai.escapy.context.game.screen.EscapyScreen;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author Henry on 28/06/17.
 */
public class AnnotationProcessor implements ScreenMapProcessorExecutor {


	@Override
	public Map<String, EscapyScreen> processScreenMap(EscapyScreen screen, Map<String, EscapyScreen> map) {

		for (Annotation annotation: screen.getClass().getAnnotations()) {

			ScreenMapProcessor processor = annotation.annotationType().getAnnotation(ScreenMapProcessor.class);
			if (processor != null) {
				try {
					ScreenMapProcessorExecutor executor = processor.value().newInstance();
					return executor.processScreenMap(screen, map);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}

}
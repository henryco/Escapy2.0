package net.irregular.escapy.map.object;

import net.irregular.escapy.engine.env.utils.Named;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;

/**
 * @author Henry on 11/07/17.
 */
public interface GameObject extends EscapyRenderable, Named {

	ObjectDetails getObjectDetails();

	default String getName() {
		return getObjectDetails().getName();
	}
}

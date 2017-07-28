package net.irregular.escapy.engine.group.container;

import net.irregular.escapy.engine.env.utils.EscapyObject;
import net.irregular.escapy.engine.group.render.core.EscapyRenderer;

/**
 * @author Henry on 28/07/17.
 */
public interface EscapyRendererContainer<T extends EscapyObject> extends EscapyRenderer {

	EscapyRenderer switchRenderer(T rendererSource);
}

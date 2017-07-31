package net.irregular.escapy.group.container;

import net.irregular.escapy.env.utils.EscapyObject;
import net.irregular.escapy.group.render.core.EscapyRenderer;

/**
 * @author Henry on 28/07/17.
 */
public interface EscapyRendererContainer<T extends EscapyObject> extends EscapyRenderer {

	EscapyRenderer switchRenderer(T rendererSource);
}

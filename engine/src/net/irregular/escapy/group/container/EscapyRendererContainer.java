package net.irregular.escapy.group.container;

import net.irregular.escapy.group.render.core.EscapyRenderer;
import net.irregular.escapy.utils.EscapyObject;

/**
 * @author Henry on 28/07/17.
 */
public interface EscapyRendererContainer<T extends EscapyObject> extends EscapyRenderer {

	EscapyRenderer switchRenderer(T rendererSource);
}

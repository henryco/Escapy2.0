package net.tindersamurai.escapy.group.container;

import net.tindersamurai.escapy.group.render.core.EscapyRenderer;
import net.tindersamurai.escapy.utils.EscapyObject;

/**
 * @author Henry on 28/07/17.
 */
public interface EscapyRendererContainer<T extends EscapyObject> extends EscapyRenderer {

	EscapyRenderer switchRenderer(T rendererSource);
}

package net.irregular.escapy.engine.group.container;

import net.irregular.escapy.engine.group.map.core.location.EscapyLocationContainer;
import net.irregular.escapy.engine.group.render.core.EscapyRenderer;

/**
 * @author Henry on 28/07/17.
 */
public interface EscapyGroupContainer {

	boolean initialize();

	EscapyLocationContainer getLocationContainer();
	EscapyRenderer getRenderer();

}

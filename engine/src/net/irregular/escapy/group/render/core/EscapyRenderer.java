package net.irregular.escapy.group.render.core;

import net.irregular.escapy.environment.utils.EscapyObject;
import net.irregular.escapy.graphic.screen.Wipeable;

/**
 * @author Henry on 20/07/17.
 */
public interface EscapyRenderer extends Wipeable, EscapyObject {

	void render(float delta);

	void resize(int width, int height);

	<T> T getRendererAttribute(String name);
}
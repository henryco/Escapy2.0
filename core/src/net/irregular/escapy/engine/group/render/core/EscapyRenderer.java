package net.irregular.escapy.engine.group.render.core;

import net.irregular.escapy.engine.env.utils.EscapyObject;
import net.irregular.escapy.engine.graphic.screen.Wipeable;

/**
 * @author Henry on 20/07/17.
 */
public interface EscapyRenderer extends Wipeable, EscapyObject {

	void render(float delta);

	void resize(int width, int height);

	<T> T getRendererAttribute(String name);
}
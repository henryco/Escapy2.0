package net.irregular.escapy.engine.group.render.core;

import net.irregular.escapy.engine.env.utils.Named;
import net.irregular.escapy.engine.graphic.screen.Wipeable;

/**
 * @author Henry on 20/07/17.
 */
public interface EscapyRenderer extends Wipeable, Named {

	void render(float delta);

	void resize(int width, int height);

}
package net.irregular.escapy.engine.map.object;

import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;

/**
 * @author Henry on 12/07/17.
 */
public interface GameObjectRenderer<T extends GameObject> {

	void bindGameObject(T gameObject);
	EscapyRenderable getRenderer();
}

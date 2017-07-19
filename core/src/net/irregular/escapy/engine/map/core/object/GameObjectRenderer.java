package net.irregular.escapy.engine.map.core.object;

import com.badlogic.gdx.utils.Disposable;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;

/**
 * @author Henry on 12/07/17.
 */
public interface GameObjectRenderer<T extends GameObject> extends Disposable {

	void bindGameObject(T gameObject);
	EscapyRenderable getRenderer();
}

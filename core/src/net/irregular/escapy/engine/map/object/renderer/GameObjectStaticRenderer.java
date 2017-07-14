package net.irregular.escapy.engine.map.object.renderer;

import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.map.object.GameObjectRenderer;
import net.irregular.escapy.engine.map.object.imp.GameObjectStatic;

/**
 * @author Henry on 14/07/17.
 */
public class GameObjectStaticRenderer implements GameObjectRenderer<GameObjectStatic> {

	private GameObjectStatic gameObject;


	@Override
	public void bindGameObject(GameObjectStatic gameObject) {
		this.gameObject = gameObject;
	}

	@Override
	public EscapyRenderable getRenderer() {

		return null;
	}

	@Override
	public void dispose() {

	}
}

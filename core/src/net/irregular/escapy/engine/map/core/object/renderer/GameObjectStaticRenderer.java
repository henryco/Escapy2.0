package net.irregular.escapy.engine.map.core.object.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.map.core.object.GameObjectRenderer;
import net.irregular.escapy.engine.map.core.object.imp.GameObjectStatic;

/**
 * @author Henry on 14/07/17.
 */
public class GameObjectStaticRenderer implements GameObjectRenderer<GameObjectStatic> {


	private final EscapyRenderable renderable;
	private final Sprite[] sprites;

	private GameObjectStatic gameObject;

	public GameObjectStaticRenderer() {
		this.renderable = new StaticRenderable();
		this.sprites = new Sprite[3];
	}


	@Override
	public void bindGameObject(GameObjectStatic gameObject) {

		this.gameObject = gameObject;

		String[] paths = {
				gameObject.getTexturePath().getTexture(),
				gameObject.getTexturePath().getTextureLight(),
				gameObject.getTexturePath().getTextureNormal()
		};

		for (int i = 0; i < 3; i++) {
			if (paths[i] != null && !paths[i].isEmpty()) {
				sprites[i] = new Sprite(new Texture(Gdx.files.external(paths[i])));
				sprites[i].setScale(gameObject.getObjectDetails().getScale());
			}
		}
	}


	@Override
	public EscapyRenderable getRenderer() {
		return renderable;
	}


	@Override
	public void dispose() {
		for (Sprite sprite: sprites)
			sprite.getTexture().dispose();
	}


	private class StaticRenderable implements EscapyRenderable {

		@Override
		public void renderGraphics(Batch batch) {
			draw(sprites[0], batch);
		}

		@Override
		public void renderLightMap(Batch batch) {
			draw(sprites[1], batch);
		}

		@Override
		public void renderNormalsMap(Batch batch) {
			draw(sprites[2], batch);
		}

		private void draw(Sprite sprite, Batch batch) {
			if (sprite != null) {
				setSpritePosition(sprite, gameObject);
				sprite.draw(batch);
			}
		}

	}

	private static void setSpritePosition(Sprite sprite,
										  GameObjectStatic gameObject) {
		float[] position = gameObject.getObjectDetails().position;
		sprite.setPosition(position[0], position[1]);
	}

}

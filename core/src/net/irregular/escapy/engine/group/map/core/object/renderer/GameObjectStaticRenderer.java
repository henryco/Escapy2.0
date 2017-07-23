package net.irregular.escapy.engine.group.map.core.object.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.group.map.core.object.EscapyGameObjectRenderer;
import net.irregular.escapy.engine.group.map.core.object.imp.GameObjectStatic;

/**
 * @author Henry on 14/07/17.
 */
public class GameObjectStaticRenderer implements EscapyGameObjectRenderer<GameObjectStatic> {


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
				sprites[i] = new Sprite(new Texture(Gdx.files.internal(paths[i])));

				if (gameObject.getObjectDetails().size[0] > 0 && gameObject.getObjectDetails().size[1] > 0)
					sprites[i].setSize(gameObject.getObjectDetails().size[0], gameObject.getObjectDetails().size[1]);
				sprites[i].setFlip(gameObject.getObjectDetails().flip[0], gameObject.getObjectDetails().flip[1]);
				sprites[i].setScale(gameObject.getObjectDetails().getScale());
			}
		}
	}

	@Override
	public float getRenderedWidth() {
		return sprites[0] != null ? sprites[0].getWidth() : 0;
	}

	@Override
	public float getRenderedHeight() {
		return sprites[0] != null ? sprites[0].getHeight() : 0;
	}

	@Override
	public void setPosition(float x, float y) {
		gameObject.getObjectDetails().position[0] = x;
		gameObject.getObjectDetails().position[1] = y;
	}

	@Override
	public void setScale(float scale) {
		for (Sprite s: sprites) if (s != null) s.setScale(scale);
		gameObject.getObjectDetails().setScale(scale);
	}

	@Override
	public void setFlip(boolean x, boolean y) {
		for (Sprite s: sprites) if (s != null) s.setFlip(x, y);
		gameObject.getObjectDetails().setFlip(new boolean[]{x, y});
	}

	@Override
	public void setSize(float w, float h) {
		for (Sprite s: sprites) if (s != null) s.setSize(w, h);
		gameObject.getObjectDetails().setSize(new float[]{w, h});
	}

	@Override
	public EscapyRenderable getRenderer() {
		return renderable;
	}


	@Override
	public void dispose() {
		for (Sprite sprite: sprites)
			if (sprite != null)
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

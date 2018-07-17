package net.tindersamurai.escapy.components.model.plain;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.model.texture.EscapyTextureData;
import net.tindersamurai.escapy.map.model.texture.IEscapyTexture;
import net.tindersamurai.escapy.utils.files.EscapyFiles;

@Log
public class StaticTexture implements IEscapyModel, IEscapyTexture {

	private Sprite[] sprites; // diff, normal, light

	public StaticTexture (EscapyTextureData data) {
		log.info(data.toString());
		this.sprites = new Sprite[] {
				EscapyFiles.loadSprite(data.diffuse),
				EscapyFiles.loadSprite(data.normals),
				EscapyFiles.loadSprite(data.shadows)
		};

		for (Sprite sprite : sprites)
			initialize(sprite, data);
	}

	private static void initialize (Sprite sprite, IEscapyTexture data) {
		if (sprite == null) return;

		sprite.setScale(data.getScaleX(), data.getScaleY());
		sprite.setFlip(data.isFlipX(), data.isFlipY());
		sprite.setPosition(data.getX(), data.getY());

		sprite.setSize (
				data.getWidth() == 0 ? sprite.getWidth() : data.getWidth(),
				data.getHeight() == 0 ? sprite.getHeight() : data.getHeight()
		);
	}

	private static void draw (Sprite sprite, IEscapyCamera camera, Batch batch) {
		if (sprite == null) return;
		batch.setProjectionMatrix(camera.getProjection());
		sprite.draw(batch);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		draw(sprites[0], camera, batch);
	}

	@Override
	public void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) {
		draw(sprites[1], camera, batch);
	}

	@Override
	public void renderShadowMap(IEscapyCamera camera, Batch batch, float delta) {
		draw(sprites[2], camera, batch);
	}

	@Override
	public float getX() {
		return sprites[0].getX();
	}

	@Override
	public float getY() {
		return sprites[0].getY();
	}

	@Override
	public float getScaleX() {
		return sprites[0].getScaleX();
	}

	@Override
	public float getScaleY() {
		return sprites[0].getScaleY();
	}

	@Override
	public float getWidth() {
		return sprites[0].getWidth();
	}

	@Override
	public float getHeight() {
		return sprites[0].getHeight();
	}

	@Override
	public boolean isFlipX() {
		return sprites[0].isFlipX();
	}

	@Override
	public boolean isFlipY() {
		return sprites[0].isFlipY();
	}
}
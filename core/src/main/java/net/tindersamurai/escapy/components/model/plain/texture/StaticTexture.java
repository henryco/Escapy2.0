package net.tindersamurai.escapy.components.model.plain.texture;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.model.sprite.IEscapySpriteProvider;
import net.tindersamurai.escapy.map.model.texture.EscapyTextureData;
import net.tindersamurai.escapy.map.model.texture.IEscapyTextureData;
import net.tindersamurai.escapy.utils.files.EscapyFiles;

import java.util.function.Consumer;

@Log
public class StaticTexture implements IEscapyModel, IEscapySpriteProvider {

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

	private static void initialize (Sprite sprite, IEscapyTextureData data) {
		if (sprite == null) return;

		log.info("Initialize sprite: " + sprite);

		sprite.setScale(data.getScaleX(), data.getScaleY());
		sprite.setFlip(data.isFlipX(), data.isFlipY());
		sprite.setPosition(data.getX(), data.getY());
		sprite.setRotation(data.getRotation());

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
	public void apply(Consumer<Sprite> s) {
		for (val sprite : sprites) s.accept(sprite);
	}

	@Override
	public Sprite provideEffectiveSprite() {
		return sprites[0] != null ? sprites[0] : sprites[1] != null ? sprites[1] : sprites[2];
	}
}
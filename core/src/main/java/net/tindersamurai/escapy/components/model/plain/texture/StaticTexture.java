package net.tindersamurai.escapy.components.model.plain.texture;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.model.sprite.IEscapySpriteBinder;
import net.tindersamurai.escapy.map.model.texture.EscapyTextureData;
import net.tindersamurai.escapy.utils.files.EscapyFiles;

import java.util.function.Consumer;

import static net.tindersamurai.escapy.graphic.IEscapyRenderable.*;

@Log
public class StaticTexture implements IEscapyModel, IEscapySpriteBinder {

	private Sprite[] sprites; // diff, normal, light
	private float[] bindPadding = {0, 0};

	public StaticTexture (EscapyTextureData data) {
		log.info(data.toString());
		this.sprites = new Sprite[] {
				EscapyFiles.loadSprite(data.diffuse),
				EscapyFiles.loadSprite(data.normals),
				EscapyFiles.loadSprite(data.shadows)
		};

		for (Sprite sprite : sprites)
			data.initializeSprite(sprite);
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
		for (val sprite : sprites)
			if (sprite != null)
				s.accept(sprite);
	}

	@Override
	public Sprite getEffectiveSprite() {
		return sprites[0] != null ? sprites[0] : sprites[1] != null ? sprites[1] : sprites[2];
	}

	@Override
	public float[] getBindPadding() {
		return new float[]{bindPadding[0], bindPadding[1]};
	}

	@Override
	public void setBindPadding(float left, float top) {
		this.bindPadding = new float[]{left, top};
	}
}
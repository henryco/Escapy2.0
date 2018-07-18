package net.tindersamurai.escapy.components.model.plain.texture;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.screen.Resolution;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.utils.files.EscapyFiles;

@Log
public class FullStaticTexture implements IEscapyModel {

	private final Sprite[] maps;

	public FullStaticTexture(
			Resolution resolution,
			String diffuseFile,
			String normalsFile,
			String shadowsFile
	) {

		log.info("***\n\nFULL STATIC TEXTURE FILE: " + diffuseFile);
		log.info("FULL STATIC TEXTURE DIM: " + resolution + "\n\n***");

		this.maps = new Sprite[] {
				EscapyFiles.loadSprite(diffuseFile),
				EscapyFiles.loadSprite(normalsFile),
				EscapyFiles.loadSprite(shadowsFile)
		};

		for (Sprite map : maps)
			prepareSprite(resolution.width, resolution.height, map);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		draw(camera, batch, maps[0]);
	}

	@Override
	public void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) {
		draw(camera, batch, maps[1]);
	}

	@Override
	public void renderShadowMap(IEscapyCamera camera, Batch batch, float delta) {
		draw(camera, batch, maps[2]);
	}

	private static void draw(IEscapyCamera camera, Batch batch, Sprite sprite) {
		if (sprite == null) return;
		float[] pos = camera.getPosition();
		camera.setCameraPosition(sprite.getWidth() * .5f, sprite.getHeight() * .5f);
		batch.setProjectionMatrix(camera.update().getProjection());
		sprite.draw(batch);
		camera.setCameraPosition(pos);
		camera.update();
	}

	private static void prepareSprite(float width, float height, Sprite sprite) {
		if (sprite == null) return;

		log.info("SPRITE: " + sprite);

		float sw = sprite.getWidth();
		float sh = sprite.getHeight();

		float scaleW = width / sw;
		float scaleH = height/ sh;

		float scale = Math.max(scaleW, scaleH);
		sprite.setScale(scale);
	}

}
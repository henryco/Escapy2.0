package net.tindersamurai.escapy.components.model.plain;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.screen.Resolution;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.utils.files.EscapyFiles;

@Log
public class StaticTexture implements IEscapyModel {

	private final Sprite[] maps;

	public StaticTexture (
			Resolution resolution,
			String diffuseFile,
			String normalsFile,
			String shadowsFile
	) {
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
		color(0.5f, 0,0, 0.5f);
		if (maps[0] != null)
			maps[0].draw(batch);
	}

	@Override
	public void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) {
		if (maps[1] != null)
			maps[1].draw(batch);
	}

	@Override
	public void renderLightMap(IEscapyCamera camera, Batch batch, float delta) {
		if (maps[2] != null)
			maps[2].draw(batch);
	}

	private static void prepareSprite(float width, float height, Sprite sprite) {
		if (sprite == null) return;

		log.info("SPRITE: " + sprite);

		float sw = sprite.getWidth();
		float sh = sprite.getHeight();

		float scaleW = width / sw;
		float scaleH = height/ sh;

		float scale = Math.max(scaleW, scaleH);

		float px = .5f * (width - sw);
		float py = .5f * (height - sh);

		System.out.println(px + " : " + py);

		sprite.setScale(scale);
//		sprite.setPosition(px, py);
	}

}
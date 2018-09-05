package net.tindersamurai.escapy.components.model.plain.texture;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.map.model.IEscapyModelDynamic;
import net.tindersamurai.escapy.map.model.sprite.IEscapySpriteBinder;
import net.tindersamurai.escapy.map.model.sprite.IEscapySpriteProvider;
import net.tindersamurai.escapy.map.model.texture.IEscapyTextureData;

import java.util.function.Consumer;

import static net.tindersamurai.escapy.graphic.IEscapyRenderable.draw;

@SuppressWarnings({"WeakerAccess", "unused"}) @Log
public class DynamicTexture implements IEscapyModelDynamic, IEscapySpriteBinder {

	private final IEscapyTextureData textureData;

	private IEscapySpriteProvider animatedSpriteProvider;
	private float[] bindPadding = {0, 0};

	public DynamicTexture (IEscapyTextureData data) {
		if (data == null)
			log.warning("No TextureData found for DynamicTexture");
		this.textureData = data;
	}

	public DynamicTexture(
			IEscapySpriteProvider provider,
			IEscapyTextureData data
	) {
		this(data);
		setSpriteProvider(provider);
	}

	@Override
	public void setSpriteProvider(IEscapySpriteProvider spriteProvider) {
		this.animatedSpriteProvider = spriteProvider;
		if (textureData == null) return;

		log.info("GOING TO INITIALIZE SPRITES");

		if (animatedSpriteProvider == null) return;
		animatedSpriteProvider.applyToAll(textureData::initializeSprite);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		if (animatedSpriteProvider == null) return;
		draw(animatedSpriteProvider.getDiffuseSprite(), camera, batch);
	}

	@Override
	public void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) {
		if (animatedSpriteProvider == null) return;
		draw(animatedSpriteProvider.getNormalsSprite(), camera, batch);
	}

	@Override
	public void renderShadowMap(IEscapyCamera camera, Batch batch, float delta) {
		if (animatedSpriteProvider == null) return;
		draw(animatedSpriteProvider.getShadowsSprite(), camera, batch);
	}

	@Override
	public void apply(Consumer<Sprite> s) {
		if (animatedSpriteProvider == null) return;
		animatedSpriteProvider.apply(s);
	}

	@Override
	public Sprite getEffectiveSprite() {
		if (animatedSpriteProvider == null) return null;
		val d = animatedSpriteProvider.getDiffuseSprite();
		if (d != null) return d;
		val n = animatedSpriteProvider.getNormalsSprite();
		if(n != null) return n;
		return animatedSpriteProvider.getShadowsSprite();
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
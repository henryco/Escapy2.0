package net.tindersamurai.escapy.components.model.plain.texture;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.map.model.IEscapyModelDynamic;
import net.tindersamurai.escapy.map.model.sprite.IEscapySpriteBinder;
import net.tindersamurai.escapy.map.model.sprite.IEscapySpriteProvider;

import java.util.function.Consumer;

import static net.tindersamurai.escapy.map.model.IEscapyRenderable.*;

@Log @NoArgsConstructor
public class DynamicTexture implements IEscapyModelDynamic, IEscapySpriteBinder {

	private IEscapySpriteProvider animatedSpriteProvider;
	private float[] bindPadding = {0, 0};

	@Override
	public void setSpriteProvider(IEscapySpriteProvider spriteProvider) {
		this.animatedSpriteProvider = spriteProvider;
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
		val d = animatedSpriteProvider.getDiffuseSprite();
		if (d != null) s.accept(d);
		val n = animatedSpriteProvider.getNormalsSprite();
		if(n != null) s.accept(n);
		val h = animatedSpriteProvider.getShadowsSprite();
		if (h != null) s.accept(h);
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
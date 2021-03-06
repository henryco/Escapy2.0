package net.tindersamurai.escapy.graphic.render.program.gl20.shader.blend;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.context.annotation.Dante;
import net.tindersamurai.escapy.context.annotation.EscapyAPI;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.ShaderFile;
import net.tindersamurai.escapy.graphic.render.program.gl20.proxy.EscapyProxyShaderProgram;
import net.tindersamurai.escapy.graphic.render.program.gl20.proxy.ProxyShaderProgram;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI @Dante @Log
public class BlendRenderer implements EscapyMultiSourceShader {

	private EscapyProxyShaderProgram shaderProgram;
	private String[] sourcesNames;
	private boolean debug;

	@EscapyAPI public BlendRenderer() {
		shaderProgram = new ProxyShaderProgram(SpriteBatch.createDefaultShader());
	}
	@EscapyAPI public BlendRenderer(ShaderFile shaderFile) {
		loadProgram(shaderFile);
	}
	@EscapyAPI public BlendRenderer(ShaderFile shaderFile, String ... sourcesNames) {
		this(shaderFile);
		setSourcesNames(sourcesNames);
	}


	@Override
	public void setSourcesNames(String... sourcesNames) {
		log.info("Source Names: " + Arrays.toString(sourcesNames));
		this.sourcesNames = new String[sourcesNames.length];
		System.arraycopy(sourcesNames, 0, this.sourcesNames, 0, sourcesNames.length);
	}

	@Override
	public void loadProgram(ShaderFile shaderFile) {
		dispose();
		log.info("load program: " + shaderFile.toString());
		shaderProgram = createProxyProgram(shaderFile, debug);
	}

	@Override
	public void dispose() {
		log.info("dispose");
		if (shaderProgram != null) {
			shaderProgram.dispose();
			log.info("disposed!");
		}
	}

	@Override
	public void draw(Batch batch, float x, float y, Texture... source) {
		begin(batch, () -> {
			bindTextures(batch, source);
			batch.begin();
			batch.draw(source[0], x, y);
			batch.end();
		});
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height, TextureRegion... source) {
		begin(batch, () -> {
			bindTextures(batch, source);
			batch.begin();
			batch.draw(source[0], x, y, width, height);
			batch.end();
		});
	}

	@Override
	public void draw(Batch batch, Sprite... source) {
		begin(batch, () -> {
			bindTextures(batch, source);
			batch.begin();
			source[0].draw(batch);
			batch.end();
		});
	}


	private void bindTextures(Batch batch, Texture... source) {
		bindTextures(batch, t -> ((Texture) t), (Object[]) source);
	}
	private void bindTextures(Batch batch, Sprite ... sources) {
		bindTextures(batch, s -> ((Sprite) s).getTexture(), (Object[]) sources);
	}
	private void bindTextures(Batch batch, TextureRegion ... sources) {
		bindTextures(batch, r -> ((TextureRegion) r).getTexture(), (Object[]) sources);
	}
	private void bindTextures(Batch batch, Function<Object, Texture> adapterFunction, Object ... sources) {
		shaderProgram.begin();
		for (int i = sources.length - 1; i >= 0; i--)
			adapterFunction.apply(sources[i]).bind(i);
		batch.setShader(shaderProgram.getShaderProgram());
		for (int i = sourcesNames.length - 1; i >= 0; i--)
			shaderProgram.setUniformi(sourcesNames[i], i);
		provideUniforms(shaderProgram);
		shaderProgram.end();
	}

	public BlendRenderer setDebug(boolean debug) {
		this.debug = debug;
		return this;
	}
}


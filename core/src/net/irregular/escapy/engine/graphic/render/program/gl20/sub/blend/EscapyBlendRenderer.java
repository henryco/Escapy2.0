package net.irregular.escapy.engine.graphic.render.program.gl20.sub.blend;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.irregular.escapy.engine.env.context.annotation.Dante;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.MultiSourceShader;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.ShaderFile;

import java.util.function.Function;

/**
 * @author Henry on 29/06/17.
 */ @EscapyAPI @Dante
public class EscapyBlendRenderer implements MultiSourceShader {

	private ShaderProgram shaderProgram;
	private String[] sourcesNames;


	@EscapyAPI public EscapyBlendRenderer() {
		shaderProgram = SpriteBatch.createDefaultShader();
	}
	@EscapyAPI public EscapyBlendRenderer(ShaderFile shaderFile) {
		loadProgram(shaderFile);
	}
	@EscapyAPI public EscapyBlendRenderer(ShaderFile shaderFile, String ... sourcesNames) {
		this(shaderFile);
		setSourcesNames(sourcesNames);
	}


	@Override
	public void setSourcesNames(String... sourcesNames) {
		this.sourcesNames = new String[sourcesNames.length];
		System.arraycopy(sourcesNames, 0, this.sourcesNames, 0, sourcesNames.length);
	}

	@Override
	public void loadProgram(ShaderFile shaderFile) {

		shaderProgram = createProgram(shaderFile);
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
		batch.setShader(shaderProgram);
		for (int i = sourcesNames.length - 1; i >= 0; i--)
			shaderProgram.setUniformi(sourcesNames[i], i);
		provideUniforms(shaderProgram);
		shaderProgram.end();
	}

}


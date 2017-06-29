package net.irregular.escapy.engine.graphic.render.port;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.irregular.escapy.engine.graphic.render.port.gl20.MultiSourceShader;

import java.util.function.Function;

/**
 * @author Henry on 29/06/17.
 */
public class EscapyBlendRenderer implements MultiSourceShader {

	private ShaderProgram shaderProgram;
	private String[] sourcesNames;

	public EscapyBlendRenderer() {}
	public EscapyBlendRenderer(ShaderFile shaderFile) {
		this();
		loadProgram(shaderFile);
	}

	@Override
	public void setSourcesNames(String... sourcesNames) {
		this.sourcesNames = new String[sourcesNames.length];
		System.arraycopy(sourcesNames, 0, this.sourcesNames, 0, sourcesNames.length);
	}

	@Override
	public void loadProgram(ShaderFile shaderFile) {
		shaderProgram = new ShaderProgram(shaderFile.VERTEX, shaderFile.FRAGMENT);
	}


	@Override
	public void draw(Batch batch, Texture... source) {
		begin(batch, () -> {
			bindTextures(batch, source);

		});
	}

	@Override
	public void draw(Batch batch, TextureRegion... source) {
		begin(batch, () -> {
			bindTextures(batch, source);

		});
	}

	@Override
	public void draw(Batch batch, Sprite... source) {
		begin(batch, () -> {
			bindTextures(batch, source);
			
		});
	}





	private void bindTextures(Batch batch, Texture... source) {
		bindTextures(batch, t -> ((Texture) t), (Object) source);
	}
	private void bindTextures(Batch batch, Sprite ... sources) {
		bindTextures(batch, s -> ((Sprite) s).getTexture(), (Object) sources);
	}
	private void bindTextures(Batch batch, TextureRegion ... sources) {
		bindTextures(batch, r -> ((TextureRegion) r).getTexture(), (Object) sources);
	}
	private void bindTextures(Batch batch, Function<Object, Texture> adapterFunction, Object ... sources) {
		shaderProgram.begin();
		for (int i = sources.length - 1; i >= 0; i--)
			adapterFunction.apply(sources[i]).bind(i);
		batch.setShader(shaderProgram);
		for (int i = sourcesNames.length - 1; i >= 0; i--)
			shaderProgram.setUniformi(sourcesNames[i], i);

		// TODO: 29/06/17 uniform loadings
		
		shaderProgram.end();
	}

}


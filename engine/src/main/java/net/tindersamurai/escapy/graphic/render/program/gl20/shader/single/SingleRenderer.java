package net.tindersamurai.escapy.graphic.render.program.gl20.shader.single;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.tindersamurai.escapy.context.annotation.EscapyAPI;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.EscapySingleSourceShader;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.ShaderFile;

/**
 * @author Henry on 30/06/17.
 */ @EscapyAPI
public class SingleRenderer implements EscapySingleSourceShader {

	private ShaderProgram shaderProgram;

	@EscapyAPI public SingleRenderer() {
		this.shaderProgram = SpriteBatch.createDefaultShader();
	}
	@EscapyAPI public SingleRenderer(ShaderFile shaderFile) {
		loadProgram(shaderFile);
	}



	@Override
	public void loadProgram(ShaderFile shaderFile) {
		dispose();
		shaderProgram = createProgram(shaderFile);
	}

	@Override
	public void dispose() {
		if (shaderProgram != null) {
			shaderProgram.dispose();
		}
	}

	@Override
	public void draw(Batch batch, float x, float y, Texture source) {
		begin(batch, () -> {
			bindProgram(batch);
			batch.begin();
			batch.draw(source, x, y);
			batch.end();
		});
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height, TextureRegion source) {
		begin(batch, () -> {
			bindProgram(batch);
			batch.begin();
			batch.draw(source, x, y, width, height);
			batch.end();
		});
	}

	@Override
	public void draw(Batch batch, Sprite source) {
		begin(batch, () -> {
			bindProgram(batch);
			batch.begin();
			source.draw(batch);
			batch.end();
		});
	}

	private void bindProgram(Batch batch) {
		shaderProgram.begin();
		batch.setShader(shaderProgram);
		provideUniforms(shaderProgram);
		shaderProgram.end();
	}

}

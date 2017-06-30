package net.irregular.escapy.engine.graphic.render.program.gl20.shader.sub.single;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.graphic.render.program.gl20.shader.core.ShaderFile;
import net.irregular.escapy.engine.graphic.render.program.gl20.shader.core.SingleSourceShader;

/**
 * @author Henry on 30/06/17.
 */ @EscapyAPI
public class EscapySingleRenderer implements SingleSourceShader {

	private ShaderProgram shaderProgram;

	@EscapyAPI public EscapySingleRenderer() {
		this.shaderProgram = SpriteBatch.createDefaultShader();
	}
	@EscapyAPI public EscapySingleRenderer(ShaderFile shaderFile) {
		loadProgram(shaderFile);
	}



	@Override
	public void loadProgram(ShaderFile shaderFile) {
		shaderProgram = createProgram(shaderFile);
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

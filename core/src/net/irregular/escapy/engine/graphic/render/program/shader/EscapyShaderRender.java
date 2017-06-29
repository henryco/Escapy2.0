package net.irregular.escapy.engine.graphic.render.program.shader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Date;
import java.util.function.Consumer;

// TODO: Auto-generated Javadoc

/**
 * The Class EscapyShaderRender.
 */
public abstract class EscapyShaderRender {


	public final Uniforms<Float> floatUniforms = new Uniforms<>(Float.class);
	public final Uniforms<Integer> intUniforms = new Uniforms<>(Integer.class);
	public final Uniforms<Float[]> floatArrUniforms = new Uniforms<>(Float[].class);
	public final Uniforms<Integer[]> intArrUniforms = new Uniforms<>(Integer[].class);

	protected Consumer<ShaderProgram> shaderLoader = program -> {};

	protected final int id;

	protected SpriteBatch batcher;
	

	public EscapyShaderRender() {
		this.id = generateID();
		this.batcher = new SpriteBatch();
	}
	

	public EscapyShaderRender(int id) {
		this.id = id;
		this.batcher = new SpriteBatch();
	}


	public abstract String toString();
	
	public abstract EscapyShaderRender initShaderProgram(String VERTEX, String FRAGMENT);

	protected void checkStatus(ShaderProgram program) {
		System.err.println(program.isCompiled() ? "COMPILED: "+this.toString() : "ERROR: "+this.toString()+"\n"+program.getLog()+"\n");
		if (!program.isCompiled()) {
			FileHandle file = Gdx.files.local("error_log.txt");
			file.writeString(new Date(TimeUtils.millis()).toString()+"\nERROR: "+this.toString()+"\n"+program.getLog()+"\n", true);
		}
	}

	public void drawSprite(Sprite sprite, OrthographicCamera camera) {
		camera.update(); //FIXME
		this.batcher.setProjectionMatrix(camera.combined);
		this.batcher.begin();
		sprite.draw(batcher);
		this.batcher.end();
	}


	public void drawTexture(Texture texture, OrthographicCamera camera, float x, float y) {
		this.batcher.setProjectionMatrix(camera.combined);
		camera.update();
		this.batcher.begin();
		this.batcher.draw(texture, x, y);
		this.batcher.end();
	}

	public void drawTextureRegion(TextureRegion region, OrthographicCamera camera, float x, float y, float width,
								  float height) {
		this.batcher.setProjectionMatrix(camera.combined);
		camera.update();
		this.batcher.begin();
		this.batcher.draw(region, x, y, width, height);
		this.batcher.end();
	}

	public EscapyShaderRender setSpriteBatch(SpriteBatch batcher) {
		this.batcher = batcher;
		return this;
	}

	protected int generateID() {
		return this.hashCode();
	}


	public int getID() {
		return this.id;
	}
	
	protected String removeFRAG(String url) {
		//.frag
		StringBuilder strb = new StringBuilder(url);
		if (strb.charAt(strb.length()-5) == '.')
			strb.delete(strb.length()-5, strb.length());
		return strb.toString();
	}
	
}

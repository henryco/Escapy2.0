package net.irregular.escapy.engine.graphic.render.program.shader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.env.context.game.Escapy;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.ShaderFile;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.uniform.StandardUniforms;
import net.irregular.escapy.engine.graphic.render.program.gl20.sub.blend.BlendRendererExtended;
import net.irregular.escapy.engine.graphic.render.program.gl20.sub.blend.EscapyUniformBlender;

import static java.io.File.separator;

/**
 * @author Henry on 02/07/17.
 */ @EscapyAPI
public class EscapyVolumeLight {

 	public static boolean debug = false;
	private static final String DIR_PATH = Escapy.getWorkDir()+separator+"shaders"+separator +"light"+separator+"volume"+separator+"dynamicLights";
	private final EscapyUniformBlender uniformBlender;

	public EscapyVolumeLight() {
		this(new BlendRendererExtended().setDebug(debug));
	}

	protected EscapyVolumeLight(EscapyUniformBlender uniformBlender) {

		this.uniformBlender = uniformBlender;
		this.uniformBlender.loadProgram(new ShaderFile(
				Gdx.files.internal(DIR_PATH + ".vert").readString(),
				Gdx.files.internal(DIR_PATH + ".frag").readString()
		));
		init();

		setAmbientIntensity(0.75f);
		setDirectIntensity(0.2f);
		setShadowIntensity(8);
		setSpriteSize(1);
		setThreshold(0);
		setHeight(0);
	}


	private void init() {

		StandardUniforms uniforms = uniformBlender.getStandardUniforms();
		uniformBlender.setSourcesNames("colorMap", "normalMap", "maskMap");
		uniforms.addFloatArrayUniform("fieldSize");
		uniforms.addFloatUniform("ambientIntensity");
		uniforms.addFloatUniform("directIntensity");
		uniforms.addFloatUniform("shadowIntensity");
		uniforms.addFloatUniform("spriteSize");
		uniforms.addFloatUniform("height");
		uniforms.addFloatUniform("threshold");
	}




	public void draw(Batch batch, float x, float y, Texture colorMap, Texture normalMap, Texture maskMap) {
		uniformBlender.draw(batch, x, y, colorMap, normalMap, maskMap);
	}
	public void draw(Batch batch, Sprite colorMap, Sprite normalMap, Sprite maskMap) {
		uniformBlender.draw(batch, colorMap, normalMap, maskMap);
	}
	public void draw(Batch batch, float x, float y, float width, float height,
					 TextureRegion colorMap, TextureRegion normalMap, TextureRegion maskMap) {
		uniformBlender.draw(batch, x, y, width, height, colorMap, normalMap, maskMap);
	}





//	---------------------------------------- SET ---------------------------------------------
	public void setFieldSize(float width, float height) {
		uniformBlender.getStandardUniforms().setFloatArrayUniform("fieldSize", width, height);
	}
	public void setAmbientIntensity(float ambientIntensity) {
		uniformBlender.getStandardUniforms().setFloatUniform("ambientIntensity", ambientIntensity);
	}
	public void setDirectIntensity(float directIntensity) {
		uniformBlender.getStandardUniforms().setFloatUniform("directIntensity", directIntensity);
	}
	public void setShadowIntensity(float shadowIntensity) {
		uniformBlender.getStandardUniforms().setFloatUniform("shadowIntensity", shadowIntensity);
	}
	public void setSpriteSize(float spriteSize) {
		uniformBlender.getStandardUniforms().setFloatUniform("spriteSize", spriteSize);
	}
	public void setHeight(float height) {
		uniformBlender.getStandardUniforms().setFloatUniform("height", height);
	}
	public void setThreshold(float threshold) {
		uniformBlender.getStandardUniforms().setFloatUniform("threshold", threshold);
	}




//	---------------------------------------- GET --------------------------------------------
	public Float[] getFieldSize() {
		return uniformBlender.getStandardUniforms().getFloatArrayUniform("fieldSize");
	}
	public float getAmbientIntensity() {
		return uniformBlender.getStandardUniforms().getFloatUniform("ambientIntensity");
	}
	public float getDirectIntensity() {
		return uniformBlender.getStandardUniforms().getFloatUniform("directIntensity");
	}
	public float getShadowIntensity() {
		return uniformBlender.getStandardUniforms().getFloatUniform("shadowIntensity");
	}
	public float getSpriteSize() {
		return uniformBlender.getStandardUniforms().getFloatUniform("spriteSize");
	}
	public float getHeight() {
		return uniformBlender.getStandardUniforms().getFloatUniform("height");
	}
	public float getThreshold() {
		return uniformBlender.getStandardUniforms().getFloatUniform("threshold");
	}



}

package net.irregular.escapy.engine.graphic.render.program.shader;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.ShaderFile;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.uniform.StandardUniforms;
import net.irregular.escapy.engine.graphic.render.program.gl20.sub.blend.EscapyBlendRendererExtended;
import net.irregular.escapy.engine.graphic.render.program.gl20.sub.blend.EscapyUniformBlender;
import net.irregular.escapy.engine.graphic.screen.Resolution;

/**
 * @author Henry on 30/06/17.
 */ @EscapyAPI
public class AbsLightSource {

	private final EscapyUniformBlender uniformBlender;

	public AbsLightSource() {
		this(new EscapyBlendRendererExtended(),
				new ShaderFile(
						Gdx.files.internal("/shaders/light/source/lightSrc.vert").readString(),
						Gdx.files.internal("/shaders/light/source/lightSrc_N.frag").readString()
				)
		);
	}

	protected AbsLightSource(EscapyUniformBlender uniformBlender, ShaderFile shaderFile) {
		this.uniformBlender = uniformBlender;
		initBlender(shaderFile);
	}


	private void initBlender(ShaderFile shaderFile) {

		StandardUniforms uniforms = uniformBlender.getStandardUniforms();
		uniforms.addFloatUniform("u_coeff");
		uniforms.addFloatUniform("u_angCorrect");
		uniforms.addFloatArrayUniform("u_color");
		uniforms.addFloatArrayUniform("u_fieldSize");
		uniforms.addFloatArrayUniform("u_umbra");
		uniforms.addFloatArrayUniform("u_radius");
		uniforms.addFloatArrayUniform("u_angles");

		uniformBlender.setSourcesNames("targetMap", "u_lightMap");
		uniformBlender.loadProgram(shaderFile);
	}


	// TODO: 01/07/17 create interface
	public void draw(Batch batch, float x, float y, Texture target, Texture map) {
		uniformBlender.draw(batch, x, y, target, map);
	}

	public void draw(Batch batch, float x, float y, float width, float height, TextureRegion target, TextureRegion map) {
		uniformBlender.draw(batch, x, y, width, height, target, map);
	}

	public void draw(Batch batch, Sprite target, Sprite map) {
		uniformBlender.draw(batch, target, map);
	}



	public void setColor(Color color) {
		uniformBlender.getStandardUniforms().setFloatArrayUniform("u_color", color.r, color.g, color.b);
	}
	public Color getColor() {
		Float[] color = uniformBlender.getStandardUniforms().getFloatArrayUniform("u_color");
		return new Color(color[0], color[1], color[2], 1f);
	}

	public void setCorrect(float correct) {
		uniformBlender.getStandardUniforms().setFloatUniform("u_angCorrect", correct);
	}
	public float getCorrect() {
		return uniformBlender.getStandardUniforms().getFloatUniform("u_angCorrect");
	}

	public void setCoeff(float coeff) {
		uniformBlender.getStandardUniforms().setFloatUniform("u_coeff", coeff);
	}
	public float getCoeff() {
		return uniformBlender.getStandardUniforms().getFloatUniform("u_coeff");
	}

	public void setResolution(Resolution resolution) {
		uniformBlender.getStandardUniforms().setFloatArrayUniform("u_fieldSize",
				(float)resolution.width, (float)resolution.height
		);
	}
	public Resolution getResolution() {
		Float[] res = uniformBlender.getStandardUniforms().getFloatArrayUniform("u_fieldSize");
		return new Resolution(res[0].intValue(), res[1].intValue());
	}

	public void setRadius(float radiusMin, float radiusMax) {
		uniformBlender.getStandardUniforms().setFloatArrayUniform("u_radius", radiusMin, radiusMax);
	}
	public Float[] getRadius() {
		return uniformBlender.getStandardUniforms().getFloatArrayUniform("u_radius");
	}

	public void setUmbra(float coeff, float power) {
		uniformBlender.getStandardUniforms().setFloatArrayUniform("u_umbra", coeff, power);
	}
	public Float[] getUmbra() {
		return uniformBlender.getStandardUniforms().getFloatArrayUniform("u_umbra");
	}

	public void setAngles(float rot, float size) {
		uniformBlender.getStandardUniforms().setFloatArrayUniform("u_angles", rot, size);
	}
	public Float[] getAngles() {
		return uniformBlender.getStandardUniforms().getFloatArrayUniform("u_angles");
	}


}

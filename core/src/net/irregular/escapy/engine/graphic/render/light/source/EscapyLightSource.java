package net.irregular.escapy.engine.graphic.render.light.source;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.ShaderFile;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.uniform.StandardUniforms;
import net.irregular.escapy.engine.graphic.render.program.gl20.sub.blend.BlendRendererExtended;
import net.irregular.escapy.engine.graphic.render.program.gl20.sub.blend.EscapyUniformBlender;
import net.irregular.escapy.engine.graphic.screen.Resolution;

/**
 * @author Henry on 30/06/17.
 */
@EscapyAPI
public class EscapyLightSource {

	public static boolean debug = false;
	private final EscapyUniformBlender uniformBlender;


	public EscapyLightSource() {
		this(new BlendRendererExtended().setDebug(debug), new ShaderFile(VERT, FRAG));
	}
	public EscapyLightSource(EscapyUniformBlender uniformBlender, ShaderFile shaderFile) {
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
	/**
	 * Create and draw light source
	 * @param target texture where render into
	 * @param map lightmap, actually might be TARGET or null
	 */
	public void draw(Batch batch, float x, float y, Texture target, Texture map) {
		uniformBlender.draw(batch, x, y, target, map);
	}

	/**
	 * Create and draw light source
	 * @param target texture where render into
	 * @param map lightmap, actually might be TARGET or null
	 */
	public void draw(Batch batch, float x, float y, float width, float height, TextureRegion target, TextureRegion map) {
		uniformBlender.draw(batch, x, y, width, height, target, map);
	}

	/**
	 * Create and draw light source
	 * @param target texture where render into
	 * @param map lightmap, actually might be TARGET or null
	 */
	public void draw(Batch batch, Sprite target, Sprite map) {
		uniformBlender.draw(batch, target, map);
	}







//	---------------------------------------- SET ---------------------------------------------
	public void setColor(Color color) {
		uniformBlender.getStandardUniforms().setFloatArrayUniform("u_color", color.r, color.g, color.b);
	}
	public void setCorrect(float correct) {
		uniformBlender.getStandardUniforms().setFloatUniform("u_angCorrect", correct);
	}
	public void setCoeff(float coeff) {
		uniformBlender.getStandardUniforms().setFloatUniform("u_coeff", coeff);
	}
	public void setResolution(Resolution resolution) {
		uniformBlender.getStandardUniforms().setFloatArrayUniform("u_fieldSize",
				(float) resolution.width, (float) resolution.height
		);
	}
	public void setRadius(float radiusMin, float radiusMax) {
		uniformBlender.getStandardUniforms().setFloatArrayUniform("u_radius", radiusMin, radiusMax);
	}
	public void setUmbra(float coeff, float power) {
		uniformBlender.getStandardUniforms().setFloatArrayUniform("u_umbra", coeff, power);
	}
	/**
	 * Angles range <-1, 1>
	 */
	public void setAngles(float rot, float size) {
		uniformBlender.getStandardUniforms().setFloatArrayUniform("u_angles", rot, size);
	}






//	---------------------------------------- GET ---------------------------------------------
	public Color getColor() {
		Float[] color = uniformBlender.getStandardUniforms().getFloatArrayUniform("u_color");
		return new Color(color[0], color[1], color[2], 1f);
	}
	public float getCorrect() {
		return uniformBlender.getStandardUniforms().getFloatUniform("u_angCorrect");
	}
	public float getCoeff() {
		return uniformBlender.getStandardUniforms().getFloatUniform("u_coeff");
	}
	public Resolution getResolution() {
		Float[] res = uniformBlender.getStandardUniforms().getFloatArrayUniform("u_fieldSize");
		return new Resolution(res[0].intValue(), res[1].intValue());
	}
	public Float[] getRadius() {
		return uniformBlender.getStandardUniforms().getFloatArrayUniform("u_radius");
	}
	public Float[] getUmbra() {
		return uniformBlender.getStandardUniforms().getFloatArrayUniform("u_umbra");
	}
	public Float[] getAngles() {
		return uniformBlender.getStandardUniforms().getFloatArrayUniform("u_angles");
	}






	private static final String VERT = "#version 330 core\n" +
			"attribute vec2 a_texCoord0;\n" +
			"attribute vec3 a_position;\n" +
			"\n" +
			"uniform mat4 u_projTrans;\n" +
			"uniform vec2 u_angles;\n" +
			"uniform vec2 u_radius;\n" +
			"uniform float u_angCorrect;\n" +
			"uniform vec2 u_umbra;\n" +
			"\n" +
			"const float c_pi = radians(180);\n" +
			"\n" +
			"out vec2 v_texCoord0;\n" +
			"out vec2 v_angles;\n" +
			"out vec2 v_radius;\n" +
			"out float v_angDiff;\n" +
			"out float v_theta;\n" +
			"out float v_umbraCoeff;\n" +
			"out float v_umbraPower;\n" +
			"\n" +
			"void main(){\n" +
			"\tv_texCoord0 = a_texCoord0;\n" +
			"\tgl_Position = u_projTrans * vec4(a_position, 1.0);\n" +
			"\tv_radius = vec2(u_radius.s * 0.5, u_radius.t);\n" +
			"\tv_angles = radians((u_angles - u_angCorrect) * 360);\n" +
			"\tv_angles.s > v_angles.t ? v_angDiff = 1 : v_angDiff = 0;\n" +
			"\tv_umbraCoeff = u_umbra.s;\n" +
			"\tv_umbraPower = u_umbra.t;\n" +
			"\tif (v_angDiff == 1) v_theta = (2.0 / abs(v_angles.s - v_angles.t));\n" +
			"\telse v_theta = (2.0 / (2*c_pi - (abs(v_angles.s) + abs(v_angles.t))));\n" +
			"}";


	private static final String FRAG = "#version 330 core\n" +
			"uniform sampler2D targetMap;\n" +
			"uniform sampler2D u_lightMap;\n" +
			"\n" +
			"uniform vec2 u_fieldSize;\n" +
			"uniform vec3 u_color;\n" +
			"uniform float u_coeff;\n" +
			"\n" +
			"in vec2 v_texCoord0;\n" +
			"in vec2 v_angles;\n" +
			"in vec2 v_radius;\n" +
			"in float v_angDiff;\n" +
			"in float v_theta;\n" +
			"in float v_umbraCoeff;\n" +
			"in float v_umbraPower;\n" +
			"\n" +
			"float getMinAngle1(vec2 angles, float angle, float otherAngle) {\n" +
			"\treturn (min(abs(angles.s - angle), abs(angles.t - angle)) * otherAngle);\n" +
			"}\n" +
			"float getMinAngle2(vec2 angles, float angle, float otherAngle) {\n" +
			"\tif (angle >= 0) return (abs(angle - angles.t) * otherAngle);\n" +
			"\telse return (abs(angle - angles.s) * otherAngle);\t\n" +
			"}\n" +
			"\n" +
			"void main() {\n" +
			"\n" +
			"\tfloat radius = length(vec2((v_texCoord0 * 2.0) - 1.0));\n" +
			"\tif (radius >= v_radius.s && radius <= v_radius.t) {\n" +
			"\t\t\n" +
			"\t\tfloat normedRadius = min(radius / v_radius.t, 1.0);\n" +
			"\t\tvec4 color = vec4(u_color.rgb, (1.0 - pow(normedRadius, 2)));\t\n" +
			"\t\tvec2 norm = normalize(vec2((v_texCoord0 * 2.0) - 1.0));\n" +
			"\t\t\n" +
			"\t\t//float alphaColor = texture2D(u_lightMap, v_texCoord0).a;\n" +
			"\t\tfloat angle = atan(norm.s, norm.t);\n" +
			"\n" +
			"\t\tif (color.a != 0) {\n" +
			"\t\t\tif (v_angDiff == 1) {\n" +
			"\t\t\t\tif (angle > v_angles.s || angle < v_angles.t)\n" +
			"\t\t\t\t\tcolor.a = mix(color.a, 0, color.a * u_coeff);\n" +
			"\t\t\t\telse if (v_umbraCoeff > 0 && v_umbraPower > 0) {\n" +
			"\t\t\t\t\tfloat nrmAngle = 1.0 - getMinAngle1(v_angles, angle, v_theta);\n" +
			"\t\t\t\t\tcolor.a = color.a - v_umbraCoeff * pow(nrmAngle, v_umbraPower);\n" +
			"\t\t\t\t}\t\t\t\t\t\n" +
			"\t\t\t}\n" +
			"\t\t\telse {\n" +
			"\t\t\t\tif (angle > v_angles.s && angle < v_angles.t)\n" +
			"\t\t\t\t\tcolor.a = mix(color.a, 0, color.a * u_coeff);\n" +
			"\t\t\t\telse if (v_umbraCoeff > 0 && v_umbraPower > 0) {\n" +
			"\t\t\t\t\tfloat nrmAngle = 1.0 - getMinAngle2(v_angles, angle, v_theta);\n" +
			"\t\t\t\t\tcolor.a = color.a - v_umbraCoeff * pow(nrmAngle, v_umbraPower);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\tcolor.a *= 1.005;\n" +
			"\t\tgl_FragColor = color;\n" +
			"\t}\telse gl_FragColor = vec4(0.);\n" +
			"\n" +
			"}";

}

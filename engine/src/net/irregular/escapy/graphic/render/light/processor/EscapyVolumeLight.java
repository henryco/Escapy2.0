package net.irregular.escapy.graphic.render.light.processor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.irregular.escapy.environment.context.annotation.EscapyAPI;
import net.irregular.escapy.graphic.render.program.gl20.core.ShaderFile;
import net.irregular.escapy.graphic.render.program.gl20.core.uniform.StandardUniforms;
import net.irregular.escapy.graphic.render.program.gl20.sub.blend.BlendRendererExtended;
import net.irregular.escapy.graphic.render.program.gl20.sub.blend.EscapyUniformBlender;

/**
 * @author Henry on 02/07/17.
 */ @EscapyAPI
public class EscapyVolumeLight implements EscapyLightProcessor {

	public static boolean debug = false;
	private final String name;
	private final EscapyUniformBlender uniformBlender;

	private boolean enable;


	public EscapyVolumeLight(String name) {
		this(name, new ShaderFile(VERT, FRAG));
	}
	public EscapyVolumeLight(String name, ShaderFile shaderFile) {
		this(name, new BlendRendererExtended().setDebug(debug), shaderFile);
	}
	protected EscapyVolumeLight(String name,
								EscapyUniformBlender uniformBlender,
								ShaderFile shaderFile) {

		this.name = name;
		this.uniformBlender = uniformBlender;
		this.uniformBlender.loadProgram(shaderFile);
		init();

		setAmbientIntensity(0.45f);
		setShadowIntensity(0.65f);
		setDirectIntensity(9);
		setHeight(0.8175f);
		setSpriteSize(55);
		setThreshold(0);
		setEnable(true);
	}



	private void init() {

		StandardUniforms uniforms = uniformBlender.getStandardUniforms();
		uniformBlender.setSourcesNames("colorMap", "normalMap", "maskMap");
		uniforms.addFloatArrayUniform("fieldSize");
		uniforms.addFloatUniform("ambientIntensity");
		uniforms.addFloatUniform("directIntensity");
		uniforms.addFloatUniform("shadowIntensity");
		uniforms.addFloatUniform("spriteSize");
		uniforms.addFloatUniform("threshold");
		uniforms.addFloatUniform("height");
	}



	@Override
	public void dispose() {
		uniformBlender.dispose();
	}

	@Override
	public String getName() {
		return name;
	}




	public void draw(Batch batch, float x, float y, Texture colorMap, Texture normalMap, Texture maskMap) {
		if (enable) uniformBlender.draw(batch, x, y, colorMap, normalMap, maskMap);
	}
	public void draw(Batch batch, Sprite colorMap, Sprite normalMap, Sprite maskMap) {
		if (enable) uniformBlender.draw(batch, colorMap, normalMap, maskMap);
	}
	public void draw(Batch batch, float x, float y, float width, float height,
					 TextureRegion colorMap, TextureRegion normalMap, TextureRegion maskMap) {
		if (enable) uniformBlender.draw(batch, x, y, width, height, colorMap, normalMap, maskMap);
	}




	@Override
	public void setFieldSize(float width, float height) {
		uniformBlender.getStandardUniforms().setFloatArrayUniform("fieldSize", width, height);
	}
	@Override
	public Float[] getFieldSize() {
		return uniformBlender.getStandardUniforms().getFloatArrayUniform("fieldSize");
	}


	@Override
	public void setThreshold(float threshold) {
		uniformBlender.getStandardUniforms().setFloatUniform("threshold", threshold);
	}
	@Override
	public float getThreshold() {
		return uniformBlender.getStandardUniforms().getFloatUniform("threshold");
	}


	@Override
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	@Override
	public boolean isEnable() {
		return enable;
	}





//	---------------------------------------- SET ---------------------------------------------
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



//	---------------------------------------- GET --------------------------------------------
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






	private static final String VERT = "#version 330 core\n" +
			"attribute vec2 a_texCoord0;\n" +
			"attribute vec3 a_position;\n" +
			" \n" +
			"uniform mat4 u_projTrans;\n" +
			"\n" +
			"out vec4 v_texCoord0;\n" +
			"\n" +
			"void main() {\n" +
			"\t\n" +
			"\tv_texCoord0 = vec4(a_texCoord0, 1.0, 1.0);\n" +
			"\tgl_Position = u_projTrans * vec4(a_position, 1.0);\n" +
			"}";


	private static final String FRAG = "#version 330 core\n" +
			"\n" +
			"in vec4 v_texCoord0;\n" +
			"\n" +
			"uniform sampler2D normalMap;\n" +
			"uniform sampler2D colorMap;\n" +
			"uniform sampler2D maskMap;\n" +
			"uniform float ambientIntensity;\n" +
			"uniform float directIntensity;\n" +
			"uniform float shadowIntensity;\n" +
			"uniform float spriteSize;\n" +
			"uniform float height;\n" +
			"uniform float threshold;\n" +
			"uniform vec2 fieldSize;\n" +
			"\n" +
			"const vec3 av = vec3(0.33333);\n" +
			"const vec3 c_one = vec3(1);\n" +
			"const int c_dir[3] = int[](-1, 0, 1);\n" +
			"\n" +
			"\n" +
			"\n" +
			"vec2 getLightDirection(sampler2D image, vec2 uv, vec2 resolution, float size) {\n" +
			"\n" +
			"    vec2 dir = vec2(0);\n" +
			"    for (int x = 0; x < 3; x++){\n" +
			"        for (int y = 0; y < 3; y++){\n" +
			"            vec2 uv_dir = vec2(uv.x + (size * c_dir[x]) / resolution.s, uv.y + (size * c_dir[y]) / resolution.t);\n" +
			"            float brightness = dot(texture2D(image, uv_dir).rgb, av);\n" +
			"            vec2 direction = vec2(c_dir[x], c_dir[y]) * brightness;\n" +
			"            dir += direction;\n" +
			"        }\n" +
			"    }\n" +
			"    return normalize(dir);\n" +
			"}\n" +
			"\n" +
			"\n" +
			"\n" +
			"void main() {\n" +
			"\n" +
			"\tvec4 col = texture2D(colorMap, v_texCoord0.st);\t\n" +
			"    float a_min = col.a;\n" +
			"\n" +
			"\tif (col.a != 0) {\n" +
			"\n" +
			"        vec4 maskRGBA = texture2D(maskMap, v_texCoord0.st);\n" +
			"        col = max(maskRGBA, col);\n" +
			"\n" +
			"        if (dot(c_one, col.rgb) <= threshold) gl_FragColor = vec4(0);\n" +
			"        else {\n" +
			"\n" +
			"            vec2 uv = vec2(gl_FragCoord.st / fieldSize.st);\n" +
			"            vec3 normal = normalize(2.0 * texture2D(normalMap, uv).rgb - 1.0);\n" +
			"            normal.r *= -1;\n" +
			"\n" +
			"            float z = min(1, dot((col.rgb * directIntensity) - shadowIntensity, av));\n" +
			"\n" +
			"            vec3 direction = vec3(getLightDirection(colorMap, uv, fieldSize, spriteSize), z);\n" +
			"\n" +
			"            float a = (dot(direction, normal) * ambientIntensity);\n" +
			"            a = (min(1, max(height * col.a, a + height)));\n" +
			"\n" +
			"            vec4 finVec = vec4(col.rgb, min(a, col.a));\n" +
			"            finVec.a = min(finVec.a, a_min);\n" +
			"\n" +
			"        \tgl_FragColor = finVec;\n" +
			"        }\n" +
			"\t}\n" +
			"\telse gl_FragColor = col;\n" +
			"}";
}
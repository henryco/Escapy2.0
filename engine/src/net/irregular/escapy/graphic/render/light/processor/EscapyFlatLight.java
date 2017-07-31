package net.irregular.escapy.graphic.render.light.processor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.irregular.escapy.graphic.render.program.gl20.core.ShaderFile;
import net.irregular.escapy.graphic.render.program.gl20.core.uniform.StandardUniforms;
import net.irregular.escapy.graphic.render.program.gl20.sub.blend.BlendRendererExtended;
import net.irregular.escapy.graphic.render.program.gl20.sub.blend.EscapyUniformBlender;

/**
 * @author Henry on 27/07/17.
 */
public class EscapyFlatLight implements EscapyLightProcessor {

	public static boolean debug = false;
	private final String name;
	private final EscapyUniformBlender uniformBlender;
	private final Float[] fieldSize;
	private boolean enable;


	public EscapyFlatLight(String name) {
		this(name, new ShaderFile(VERT, FRAG));
	}
	public EscapyFlatLight(String name, ShaderFile shaderFile) {
		this(name, new BlendRendererExtended().setDebug(debug), shaderFile);
	}
	protected EscapyFlatLight(String name,
								EscapyUniformBlender uniformBlender,
								ShaderFile shaderFile) {

		this.name = name;
		this.fieldSize = new Float[2];
		this.uniformBlender = uniformBlender;
		this.uniformBlender.loadProgram(shaderFile);

		init();

		setFieldSize(0,0);
		setThreshold(0);
		setEnable(true);
	}



	private void init() {

		StandardUniforms uniforms = uniformBlender.getStandardUniforms();
		uniformBlender.setSourcesNames("colorMap", "maskMap");
		uniforms.addFloatUniform("threshold");
	}



	@Override
	public void dispose() {
		uniformBlender.dispose();
	}

	@Override
	public String getName() {
		return name;
	}



	public void draw(Batch batch, float x, float y, Texture colorMap, Texture maskMap) {
		if (enable) uniformBlender.draw(batch, x, y, colorMap, maskMap);
	}
	public void draw(Batch batch, Sprite colorMap, Sprite maskMap) {
		if (enable) uniformBlender.draw(batch, colorMap, maskMap);
	}
	public void draw(Batch batch, float x, float y, float width, float height,
					 TextureRegion colorMap, TextureRegion maskMap) {
		if (enable) uniformBlender.draw(batch, x, y, width, height, colorMap, maskMap);
	}




//	---------------------------------------- SET ---------------------------------------------
	@Override
	public void setFieldSize(float width, float height) {
		this.fieldSize[0] = width;
		this.fieldSize[1] = height;
	}
	@Override
	public void setThreshold(float threshold) {
		uniformBlender.getStandardUniforms().setFloatUniform("threshold", threshold);
	}
	@Override
	public void setEnable(boolean enable) {
		this.enable = enable;
	}



//	---------------------------------------- GET --------------------------------------------
	@Override
	public Float[] getFieldSize() {
		return fieldSize.clone();
	}
	@Override
	public float getThreshold() {
		return uniformBlender.getStandardUniforms().getFloatUniform("threshold");
	}
	@Override
	public boolean isEnable() {
		return enable;
	}




	private static final String VERT = "#version 330 core\n" +
			"attribute vec2 a_texCoord0;\n" +
			"attribute vec3 a_position;\n" +
			"\n" +
			"uniform mat4 u_projTrans;\n" +
			"\n" +
			"out vec4 v_texCoord0;\n" +
			"\n" +
			"void main() {\n" +
			"\n" +
			"\tv_texCoord0 = vec4(a_texCoord0, 1.0, 1.0);\n" +
			"\tgl_Position = u_projTrans * vec4(a_position, 1.0);\n" +
			"}";


	private static final String FRAG = "#version 330 core\n" +
			"\n" +
			"in vec4 v_texCoord0;\n" +
			"\n" +
			"uniform sampler2D colorMap;\n" +
			"uniform sampler2D maskMap;\n" +
			"uniform float threshold;\n" +
			"\n" +
			"const vec3 c_one = vec3(1);\n" +
			"\n" +
			"\n" +
			"void main() {\n" +
			"\n" +
			"\tvec4 col = texture2D(colorMap, v_texCoord0.st);\n" +
			"\n" +
			"\tif (col.a != 0) {\n" +
			"\n" +
			"        vec4 maskRGBA = texture2D(maskMap, v_texCoord0.st);\n" +
			"        col = max(maskRGBA, col);\n" +
			"\n" +
			"        if (dot(c_one.rgb, col.rgb) <= threshold)\n" +
			"            col = vec4(0);\n" +
			"\t}\n" +
			"\n" +
			"\tgl_FragColor = col;\n" +
			"}";
}
package net.tindersamurai.escapy.group.render.loader.imp;

import net.tindersamurai.escapy.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.ShaderFile;
import net.tindersamurai.escapy.graphic.render.program.gl20.shader.blend.BlendRenderer;
import net.tindersamurai.escapy.group.render.loader.RendererVoidSubLoader;
import net.tindersamurai.escapy.group.render.loader.serial.SerializedRenderer;
import net.tindersamurai.escapy.utils.array.EscapyAssociatedArray;
import net.tindersamurai.escapy.utils.array.EscapyNamedArray;
import net.tindersamurai.escapy.utils.loader.EscapyInstanceLoader;

/**
 * @author Henry on 25/07/17.
 */
public class DefaultLightShaderGroupSubLoader
		implements RendererVoidSubLoader<EscapyMultiSourceShader, SerializedRenderer> {


	private final EscapyInstanceLoader<EscapyMultiSourceShader> lightShaderLoader;
	public DefaultLightShaderGroupSubLoader(EscapyInstanceLoader<EscapyMultiSourceShader> lightShaderLoader) {
		this.lightShaderLoader = lightShaderLoader;
	}


	@Override
	public EscapyAssociatedArray<EscapyMultiSourceShader> loadRendererPart(SerializedRenderer serialized) {

		EscapyAssociatedArray<EscapyMultiSourceShader> lightShaders = new EscapyNamedArray<>(EscapyMultiSourceShader.class);
		for (SerializedRenderer.SerializedRenderGroup renderGroup : serialized.renderGroups) {

			EscapyMultiSourceShader shader = new BlendRenderer(new ShaderFile(DEF_VERT, DEF_FRAG), sources);
			if (lightShaderLoader != null)
				shader = lightShaderLoader.loadInstanceAttributes(shader, renderGroup.lightGroup.attributes);
			lightShaders.add(shader, shader.getName());
		}
		return lightShaders;
	}





	private static final String[] sources = {"targetMap", "blendMap"};
	private static final String DEF_VERT = "#version 330 core\n" +
			"attribute vec2 a_texCoord0;\n" +
			"attribute vec3 a_position;\n" +
			"\n" +
			"uniform mat4 u_projTrans;\n" +
			"\n" +
			"out vec2 v_texCoord0;\n" +
			"\n" +
			"void main(){\n" +
			"\tv_texCoord0 = a_texCoord0;\n" +
			"\tgl_Position = u_projTrans * vec4(a_position, 1.0);\n" +
			"}";
	private static final String DEF_FRAG = "#version 330 core\n" +
			"#define Blend(base, blend, funcf)\t\tvec3(funcf(base.r, blend.r), funcf(base.g, blend.g), funcf(base.b, blend.b))\n" +
			"#define BlendColorMix(base, blend) \tBlend(base, blend, BlendAddf)\n" +
			"#define BlendAddf(base, blend)\t\t\tmin(base + blend, 1.0)\n" +
			"#define BlendAverage(base, blend) \t\t((base + blend) / 2.0)\n" +
			"#define BlendColorDodgef(base, blend) \t((blend == 1.0) ? blend : min(base / (1.0 - blend), 1.0))\n" +
			"#define BlendColorDodge(base, blend) \tBlend(base, blend, BlendColorDodgef)\n" +
			"\n" +
			"#define BlendOverlayf(base, blend) \t(base < 0.5 ? (2.0 * base * blend) : (1.0 - 2.0 * (1.0 - base) * (1.0 - blend)))\n" +
			"#define BlendOverlay(base, blend) \t\tBlend(base, blend, BlendOverlayf)\n" +
			"\n" +
			"\n" +
			"uniform sampler2D targetMap;\n" +
			"uniform sampler2D blendMap;\n" +
			"\n" +
			"\n" +
			"in vec2 v_texCoord0;\n" +
			"\n" +
			"\n" +
			"void main() {\n" +
			"\t\n" +
			"\tvec4 targetRGBA = texture2D(targetMap, v_texCoord0);\n" +
			"\tif (targetRGBA.a != 0) {\n" +
			"        vec4 blendRGBA = texture2D(blendMap, v_texCoord0);\n" +
			"        vec3 color = BlendColorMix(targetRGBA, blendRGBA);\n" +
			"        color = BlendOverlay(color, targetRGBA.rgb);\n" +
			"        color = BlendAverage(color, targetRGBA.rgb);\n" +
			"        vec4 fcol = vec4(color.rgb, blendRGBA.a);\n" +
			"\n" +
			"        fcol.a = max(fcol.a, blendRGBA.a);\n" +
			"\t\tgl_FragColor = fcol;\n" +
			"\t}\n" +
			"\telse gl_FragColor = targetRGBA;\n" +
			"}";

}
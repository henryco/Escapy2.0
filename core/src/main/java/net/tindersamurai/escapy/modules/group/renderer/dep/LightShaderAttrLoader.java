package net.tindersamurai.escapy.modules.group.renderer.dep;

import com.badlogic.gdx.Gdx;
import net.tindersamurai.escapy.context.game.Escapy;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.ShaderFile;
import net.tindersamurai.escapy.graphic.render.program.gl20.shader.blend.BlendRenderer;
import net.tindersamurai.escapy.utils.files.EscapyFiles;
import net.tindersamurai.escapy.utils.loader.EscapyInstanceLoader;
import net.tindersamurai.escapy.utils.loader.EscapyInstanced;

import static java.io.File.separator;

/**
 * @author Henry on 24/07/17.
 */
public class LightShaderAttrLoader implements EscapyInstanceLoader<EscapyMultiSourceShader> {

	private static final String[] sources = {"targetMap", "blendMap"};


	private static String getDirPath() {
		return Escapy.getProperty("BLEND_SHADERS_ROOT_DIR_PATH");
	}
	private static String load(String path) {
		return Gdx.files.internal(EscapyFiles.safetyPath(path)).readString();
	}
	private static ShaderFile loadMulti(String path) {
		return new ShaderFile(load(getDirPath() + path + ".vert"), load(getDirPath() + path + ".frag"));
	}


	@EscapyInstanced("ADD_OVERLAY_STRONG")
	public EscapyMultiSourceShader loadAddOverlayStrongShader(EscapyMultiSourceShader shader) {
		String program = separator + "ADD_OVERLAY_STRONG" + separator + "ADD_OVERLAY_STRONG";
		return new BlendRenderer(loadMulti(program), sources);
	}



}
package net.irregular.escapy.environment.main.group.renderer.dep;

import com.badlogic.gdx.Gdx;
import net.irregular.escapy.engine.env.context.game.Escapy;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanced;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.irregular.escapy.engine.graphic.render.program.gl20.core.ShaderFile;
import net.irregular.escapy.engine.graphic.render.program.gl20.sub.blend.BlendRenderer;

import static java.io.File.separator;

/**
 * @author Henry on 24/07/17.
 */
public class LightShaderLoader implements EscapyInstanceLoader<EscapyMultiSourceShader> {

	private static final String[] sources = {"targetMap", "blendMap"};


	private static String getDirPath() {
		return  Escapy.getWorkDir() + separator + "shaders" + separator + "blend" + separator;
	}
	private static String load(String path) {
		return Gdx.files.internal(path).readString();
	}
	private static ShaderFile loadMulti(String path) {
		return new ShaderFile(load(getDirPath() + path + ".vert"), load(getDirPath() + path + ".frag"));
	}



	@EscapyInstanced("ADD_OVERLAY_STRONG")
	public EscapyMultiSourceShader loadAddOverlayStrongShader() {
		String program = "ADD_OVERLAY_STRONG" + separator + "ADD_OVERLAY_STRONG";
		return new BlendRenderer(loadMulti(program), sources);
	}




}
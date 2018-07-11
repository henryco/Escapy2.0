package net.tindersamurai.escapy.components.model;

import com.badlogic.gdx.Gdx;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.model.plain.LayerModel;
import net.tindersamurai.escapy.components.model.plain.StaticTexture;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFrameBuffer;
import net.tindersamurai.escapy.graphic.screen.Resolution;
import net.tindersamurai.escapy.map.model.IEscapyModel;

import javax.inject.Inject;

@Provide
@EscapyComponentFactory("model")
public class ModelFactory {

	private final EscapyGameContext context;
	private final Resolution resolution;

	@Inject
	public ModelFactory(
			Resolution defaultResolution,
			EscapyGameContext context
	) {
		this.resolution = defaultResolution;
		this.context = context;
	}

	@EscapyComponent("layer")
	public IEscapyModel layer(@Arg("args") IEscapyModel ... nested) {
		return new LayerModel (
				new EscapyFrameBuffer(resolution),
				new EscapyFrameBuffer(resolution),
				nested
		);
	}

	@EscapyComponent("texture-static")
	public IEscapyModel staticTexture(
			@Arg("diffuse") String diffuseFile,
			@Arg("normals") String normalsFile,
			@Arg("shadows") String shadowsFile
	) {
		val root = context.getConfigsFilePath();
		val a = diffuseFile == null ? null : root + diffuseFile;
		val b = normalsFile == null ? null : root + normalsFile;
		val c = shadowsFile == null ? null : root + shadowsFile;
		return new StaticTexture(resolution, a, b, c);
	}
}
package net.tindersamurai.escapy.components.model;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.model.plain.LayerModel;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFrameBuffer;
import net.tindersamurai.escapy.graphic.screen.Resolution;
import net.tindersamurai.escapy.map.model.IEscapyModel;

import javax.inject.Inject;

@Provide
@EscapyComponentFactory("model")
public class ModelFactory {

	private final EscapyFBO[] sharedBuffers;

	@Inject
	public ModelFactory(Resolution defaultResolution) {
		this.sharedBuffers = new EscapyFBO[] {
				new EscapyFrameBuffer(defaultResolution), // diffuse
				new EscapyFrameBuffer(defaultResolution), // normals
		};
	}


	@EscapyComponent("layer")
	public IEscapyModel layer(@Arg("args") IEscapyModel ... nested) {
		return new LayerModel(sharedBuffers[0], sharedBuffers[1], nested);
	}

}
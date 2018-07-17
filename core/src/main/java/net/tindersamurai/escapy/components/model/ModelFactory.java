package net.tindersamurai.escapy.components.model;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.model.plain.LayerModel;
import net.tindersamurai.escapy.components.model.plain.FullStaticTexture;
import net.tindersamurai.escapy.components.model.plain.MaskModel;
import net.tindersamurai.escapy.components.model.plain.StaticTexture;
import net.tindersamurai.escapy.components.model.plain.util.UpWrapper;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.graphic.render.program.gl10.mask.LightMask;
import net.tindersamurai.escapy.map.model.texture.EscapyTextureData;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFrameBuffer;
import net.tindersamurai.escapy.graphic.screen.Resolution;
import net.tindersamurai.escapy.map.model.IEscapyModel;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

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
	public IEscapyModel layer (
			@Arg("diffuse-fbo") UpWrapper<EscapyFBO> diffuseFbo,
			@Arg("normals-fbo") UpWrapper<EscapyFBO> normalsFbo,
			@Arg("shadows-fbo") UpWrapper<EscapyFBO> shadowsFbo,
			@Arg("args") IEscapyModel ... nested
	) {
		return new LayerModel (diffuseFbo, normalsFbo, shadowsFbo, nested);
	}

	@EscapyComponent("mask")
	public IEscapyModel mask (
			@Arg("diffuse-fbo") UpWrapper<EscapyFBO> diffuseFbo,
			@Arg("mask-fbo") UpWrapper<EscapyFBO> maskFbo
	) {
		val mask = new LightMask(resolution.width, resolution.height);
		return new MaskModel(diffuseFbo, maskFbo, mask);
	}


	@EscapyComponentFactory("helper")
	public final class HelperFactory {

		private final Map<String, UpWrapper<EscapyFBO>> fboMap;
		public HelperFactory() {
			this.fboMap = new HashMap<>();
		}

		@EscapyComponent("w-fbo")
		public UpWrapper<EscapyFBO> findOrCreateFbo(@Arg("id") String name) {
			val fbo = fboMap.get(name);
			if (fbo != null) return fbo;
			val newFbo = new UpWrapper<EscapyFBO>(new EscapyFrameBuffer(resolution));
			fboMap.put(name, newFbo);
			return newFbo;
		}

		@EscapyComponent("props")
		public EscapyTextureData textureData (
				@Arg("diffuse") String diffuse,
				@Arg("normals") String normals,
				@Arg("shadows") String shadows,
				@Arg("x") Float x,
				@Arg("y") Float y,
				@Arg("width") Float width,
				@Arg("height") Float height,
				@Arg("scaleX") Float scaleX,
				@Arg("scaleY") Float scaleY,
				@Arg("flipX") Boolean flipX,
				@Arg("flipY") Boolean flipY
		) {
			val root = context.getConfigsFilePath();
			return new EscapyTextureData(root, diffuse, normals, shadows) {{

				if (width != null) setWidth(width);
				if (height != null) setHeight(height);

				if (scaleX != null) setScaleX(scaleX);
				if (scaleY != null) setScaleY(scaleY);

				if (flipX != null) setFlipX(flipX);
				if (flipY != null) setFlipY(flipY);

				if (x != null) setX(x);
				if (y != null) setY(y);
			}};
		}

	}


	@EscapyComponentFactory("texture")
	public final class TextureFactory {

		@EscapyComponent("static-full")
		public IEscapyModel staticTextureFull (
				@Arg("diffuse") String diffuseFile,
				@Arg("normals") String normalsFile,
				@Arg("shadows") String shadowsFile
		) {
			val root = context.getConfigsFilePath();
			val a = diffuseFile == null ? null : root + diffuseFile;
			val b = normalsFile == null ? null : root + normalsFile;
			val c = shadowsFile == null ? null : root + shadowsFile;
			return new FullStaticTexture(resolution, a, b, c);
		}

		@EscapyComponent("static")
		public IEscapyModel staticTexture (
				@Arg("properties") EscapyTextureData props
		) {
			return new StaticTexture(props);
		}

	}



}
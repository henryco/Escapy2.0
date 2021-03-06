package net.tindersamurai.escapy.components.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.components.model.plain.texture.*;
import net.tindersamurai.escapy.components.model.plain.light.LightPackModel;
import net.tindersamurai.escapy.components.model.plain.light.LightSourceModel;
import net.tindersamurai.escapy.components.model.plain.light.LightTypeModel;
import net.tindersamurai.escapy.components.model.plain.shift.DummyShiftLogic;
import net.tindersamurai.escapy.components.model.plain.shift.ShiftModel;
import net.tindersamurai.escapy.components.model.plain.MaskModel;
import net.tindersamurai.escapy.components.model.plain.util.UpWrapper;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.graphic.render.light.processor.EscapyFlatLight;
import net.tindersamurai.escapy.graphic.render.light.processor.EscapyLightProcessor;
import net.tindersamurai.escapy.graphic.render.light.processor.EscapyVolumeLight;
import net.tindersamurai.escapy.graphic.render.light.source.EscapyLightSourceData;
import net.tindersamurai.escapy.graphic.render.light.source.LightSource;
import net.tindersamurai.escapy.graphic.render.program.gl10.blend.NativeSeparateBlendRenderer;
import net.tindersamurai.escapy.graphic.render.program.gl10.mask.LightMask;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.EscapyMultiSourceShader;
import net.tindersamurai.escapy.graphic.render.program.gl20.core.ShaderFile;
import net.tindersamurai.escapy.graphic.render.program.gl20.shader.blend.BlendRenderer;
import net.tindersamurai.escapy.map.model.sprite.IEscapySpriteProvider;
import net.tindersamurai.escapy.map.model.texture.EscapyTextureData;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFrameBuffer;
import net.tindersamurai.escapy.graphic.screen.Resolution;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.model.texture.IEscapyTextureData;
import net.tindersamurai.escapy.utils.files.EscapyFiles;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Provide
@EscapyComponentFactory("model")
public class ModelFactory {

	private final EscapyGameContext context;
	private final Resolution resolution;

	@Inject
	public ModelFactory (
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
			@Arg("nested") IEscapyModel ... nested
	) {
		return new LayerModel (diffuseFbo, normalsFbo, shadowsFbo, nested);
	}

	@EscapyComponent("mask")
	public IEscapyModel mask (
			@Arg("diffuse-fbo") UpWrapper<EscapyFBO> diffuseFbo,
			@Arg("mask-fbo") UpWrapper<EscapyFBO> maskFbo,
			@Arg("color") Color color,
			@Arg("scr") String src,
			@Arg("dst") String dst
	) {
		val mask = new LightMask(resolution.width, resolution.height);
		if (color != null) mask.setColor(color.r, color.g, color.b, color.a);
		if (src != null && dst != null) try {
			mask.setMaskFunc (
					(int) GL20.class.getDeclaredField(src).get(GL20.class),
					(int) GL20.class.getDeclaredField(dst).get(GL20.class)
			);
		} catch (Exception e) {
			throw new RuntimeException("Cannot load mask blend mode", e);
		}
		return new MaskModel(diffuseFbo, maskFbo, mask);
	}

	@EscapyComponent("shift")
	public IEscapyModel shift (
			// TODO some shift args
			@Arg("nested") IEscapyModel ... nested
	) {
		return new ShiftModel(new DummyShiftLogic(), nested);
	}

	@EscapyComponentFactory("helper")
	public final class HelperFactory {

		private final Map<String, UpWrapper<EscapyFBO>> fboMap;
		public HelperFactory() {
			this.fboMap = new HashMap<>();
		}

		@EscapyComponent("w-fbo")
		public UpWrapper<EscapyFBO> findOrCreateFbo(
				@Arg("id") String name
		) {
			val fbo = fboMap.get(name);
			if (fbo != null) return fbo;
			val newFbo = new UpWrapper<EscapyFBO>(new EscapyFrameBuffer(resolution));
			fboMap.put(name, newFbo);
			return newFbo;
		}

		@EscapyComponent("t-props")
		public EscapyTextureData textureData (
				@Arg("diffuse") String diffuse,
				@Arg("normals") String normals,
				@Arg("shadows") String shadows,
				@Arg("rotation") Float rotation,
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

				if (rotation != null) setRotation(rotation);

				if (x != null) setX(x);
				if (y != null) setY(y);
			}};
		}

		@EscapyComponent("l-props")
		public EscapyLightSourceData lightSourceData (
				@Arg("resolution") Integer[] resolution,
				@Arg("coefficient") Float coefficient,
				@Arg("correction") Float correction,
				@Arg("position") Float[] position,
				@Arg("radius") Float[] radius,
				@Arg("umbra") Float[] umbra,
				@Arg("angle") Float[] angle,
				@Arg("alpha") Float alpha,
				@Arg("scale") Float scale,
				@Arg("color") Color color
		) {
			return new EscapyLightSourceData () {{
				if (resolution != null) setResolution(resolution);
				if (coefficient != null) setCoefficient(coefficient);
				if (correction != null) setCorrection(correction);
				if (position != null) setPosition(position);
				if (radius != null) setRadius(radius);
				if (umbra != null) setUmbra(umbra);
				if (angle != null) setAngle(angle);
				if (alpha != null) setAlpha(alpha);
				if (scale != null) setScale(scale);
				if (color != null) setColor(color);
			}};
		}

		@EscapyComponent("color")
		public Color color (
				@Arg("RGBA8888") Integer rgba8888,
				@Arg("RGBA255") Float[] rgba,
				@Arg("RGB255") Float[] rgb,
				@Arg("r") Float r,
				@Arg("g") Float g,
				@Arg("b") Float b,
				@Arg("a") Float a
		) {
			if (rgba != null) return new Color(rgba[0] / 255f, rgba[1] / 255f, rgba[2] / 255f, rgba[3] / 255f);
			if (rgb != null) return new Color(rgb[0] / 255f, rgb[1] / 255f, rgb[2] / 255f, 1);
			if (rgba8888 != null) return new Color(rgba8888);
			return new Color(r, g, b, a);
		}
	}


	@EscapyComponentFactory("texture")
	public final class TextureFactory {

		@EscapyComponent("dynamic")
		public final IEscapyModel dynamicTexture (
				@Arg("properties") IEscapyTextureData textureData,
				@Arg("provider") IEscapySpriteProvider provider,
				@Arg("bind") Float[] bind
		) {
			return new DynamicTexture(textureData) {{
				if (bind != null) setBindPadding(bind[0], bind[1]);
				if (provider != null) setSpriteProvider(provider);
			}};
		}


		@EscapyComponent("static-full")
		public final IEscapyModel staticTextureFull (
				@Arg("diffuse") String diffuseFile,
				@Arg("normals") String normalsFile,
				@Arg("shadows") String shadowsFile,
				@Arg("bind") Float[] bind
		) {
			val root = context.getConfigsFilePath();
			val a = diffuseFile == null ? null : root + diffuseFile;
			val b = normalsFile == null ? null : root + normalsFile;
			val c = shadowsFile == null ? null : root + shadowsFile;
			return new FullStaticTexture(resolution, a, b, c) {{
				if (bind != null) setBindPadding(bind[0], bind[1]);
			}};
		}

		@EscapyComponent("static")
		public final IEscapyModel staticTexture (
				@Arg("properties") EscapyTextureData props,
				@Arg("bind") Float[] bind
		) {
			return new StaticTexture(props) {{
				if (bind != null) setBindPadding(bind[0], bind[1]);
			}};
		}

		@SafeVarargs @EscapyComponent("buffer")
		public final IEscapyModel buffer(
				@Arg("buffers") UpWrapper<EscapyFBO> ... buffers
		) {
			return new BufferModel(buffers);
		}
	}


	@EscapyComponentFactory("light")
	public final class LightFactory {

		@EscapyComponentFactory("processor")
		public final class LightProcessor {

			@EscapyComponent("flat")
			public EscapyLightProcessor flat (
					@Arg("threshold") Float threshold,
					@Arg("enable") Boolean enable
			) {
				val processor = new EscapyFlatLight(UUID.randomUUID().toString());
				processor.setFieldSize(resolution.width, resolution.height);
				if (threshold != null) processor.setThreshold(threshold);
				if (enable != null) processor.setEnable(enable);
				return processor;
			}

			@EscapyComponent("volumetric")
			public EscapyLightProcessor processor (
					@Arg("threshold") Float threshold,
					@Arg("enable") Boolean enable,
					@Arg("ambient") Float ambient,
					@Arg("direct") Float direct,
					@Arg("shadow") Float shadow,
					@Arg("height") Float height,
					@Arg("sprite") Float sprite
			) {
				val processor = new EscapyVolumeLight(UUID.randomUUID().toString());
				processor.setFieldSize(resolution.width, resolution.height);
				if (threshold != null) processor.setThreshold(threshold);
				if (enable != null) processor.setEnable(enable);
				if (direct != null) processor.setDirectIntensity(direct);
				if (ambient != null) processor.setAmbientIntensity(ambient);
				if (shadow != null) processor.setShadowIntensity(shadow);
				if (height != null) processor.setHeight(height);
				if (sprite != null) processor.setSpriteSize(sprite);
				return processor;
			}
		}

		@EscapyComponent("pack")
		public IEscapyModel lightPack (
				@Arg("processor") EscapyLightProcessor processor,
				@Arg("color-fbo") UpWrapper<EscapyFBO> colorFbo,
				@Arg("normals-fbo") UpWrapper<EscapyFBO> normalsFbo,
				@Arg("mask-fbo") UpWrapper<EscapyFBO> maskFbo
		) {
			return new LightPackModel(processor, colorFbo, normalsFbo, maskFbo);
		}

		@EscapyComponent("type")
		public IEscapyModel lightType (
				@Arg("shader") EscapyMultiSourceShader lightBlender,
				@Arg("diffuse-fbo") UpWrapper<EscapyFBO> diffuseFbo,
				@Arg("color-fbo") UpWrapper<EscapyFBO> colorFbo,
				@Arg("light-fbo") UpWrapper<EscapyFBO> lightFbo,
				@Arg("nested") IEscapyModel ... nested
		) {
			val blender = new NativeSeparateBlendRenderer();
			return new LightTypeModel (
					lightBlender, colorFbo, lightFbo, diffuseFbo, blender, nested
			);
		}

		@EscapyComponent("source")
		public IEscapyModel lightSource (
				@Arg("properties") EscapyLightSourceData props
		) {
			return new LightSourceModel(props.initialize(new LightSource (
					UUID.randomUUID().toString()
			)));
		}

		@EscapyComponent("shader")
		public EscapyMultiSourceShader blender (
				@Arg("program") String programName
		) {
			String dir = context.getProperty("BLEND_SHADERS_DIR");
			val VERT = dir + programName + "/" + programName + ".vert";
			val FRAG = dir + programName + "/" + programName + ".frag";
			return new BlendRenderer(new ShaderFile (
					Gdx.files.internal(EscapyFiles.safetyPath(VERT)).readString(),
					Gdx.files.internal(EscapyFiles.safetyPath(FRAG)).readString()
			), "targetMap", "blendMap");
		}
	}

}
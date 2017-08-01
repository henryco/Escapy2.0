package net.irregular.escapy.group.render.loader.imp;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import net.irregular.escapy.graphic.camera.EscapyCamera;
import net.irregular.escapy.graphic.render.program.gl10.blend.EscapyGLBlendRenderer;
import net.irregular.escapy.graphic.render.program.gl10.blend.NativeSeparateBlendRenderer;
import net.irregular.escapy.group.render.loader.RendererVoidSubLoader;
import net.irregular.escapy.group.render.loader.serial.SerializedBlender;
import net.irregular.escapy.group.render.loader.serial.SerializedRenderer;
import net.irregular.escapy.utils.array.EscapyAssociatedArray;
import net.irregular.escapy.utils.array.EscapyNamedArray;
import net.irregular.escapy.utils.loader.EscapyInstanceLoader;

/**
 * @author Henry on 25/07/17.
 */
public class DefaultGLBlenderGroupSubLoader
		implements RendererVoidSubLoader<EscapyGLBlendRenderer, SerializedRenderer> {


	private final EscapyInstanceLoader<EscapyGLBlendRenderer> glBlenderAttrInstLoader;
	private final EscapyCamera camera;

	public DefaultGLBlenderGroupSubLoader(EscapyInstanceLoader<EscapyGLBlendRenderer> glBlenderAttrInstLoader,
										  EscapyCamera camera) {
		this.glBlenderAttrInstLoader = glBlenderAttrInstLoader;
		this.camera = camera;
	}



	@Override
	public EscapyAssociatedArray<EscapyGLBlendRenderer> loadRendererPart(SerializedRenderer serialized) {
		EscapyAssociatedArray<EscapyGLBlendRenderer> blenders = new EscapyNamedArray<>(EscapyGLBlendRenderer.class);
		for (SerializedRenderer.SerializedRenderGroup renderGroup : serialized.renderGroups) {

			SerializedBlender serializedBlender = renderGroup.blender;

			EscapyGLBlendRenderer blender =
					new NativeSeparateBlendRenderer(serializedBlender.blendMode.loadGLMode()) {

						private float[] matrixValues;

						@Override
						public synchronized void begin(Batch batch) {

							camera.update();
							this.matrixValues = batch.getProjectionMatrix().getValues();
							batch.setProjectionMatrix(camera.getProjection());

							super.begin(batch);
						}

						@Override
						public synchronized void end(Batch batch) {

							super.end(batch);

							Matrix4 matrix = batch.getProjectionMatrix();
							matrix.set(matrixValues);
							batch.setProjectionMatrix(matrix);
						}

					};

			if (glBlenderAttrInstLoader != null)
				blender = glBlenderAttrInstLoader.loadInstanceAttributes(blender, serializedBlender.attributes);

			blenders.add(blender, serializedBlender.name);
		}
		return blenders;
	}
}

package net.irregular.escapy.engine.map.zloader.imp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.google.gson.Gson;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.map.layer.Layer;
import net.irregular.escapy.engine.map.zloader.RenderContainerLoader;
import net.irregular.escapy.engine.map.zloader.serial.SerializedRendererContainer;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;


/**
 * @author Henry on 16/07/17.
 */
public class DefaultRenderContainerLoader implements RenderContainerLoader<Collection<Layer>> {


	@Override
	public EscapyRenderable loadRenderContainer(String path, Collection<Layer> layers) {

		return new EscapyRenderable() {

			private final EscapyAssociatedArray<EscapyRenderable> container;


			{
				container = new EscapyNamedArray<>(EscapyRenderable.class);
				Gson gson = new Gson();
				Reader reader = new InputStreamReader(Gdx.files.external(path).read());
				SerializedRendererContainer serialized = gson.fromJson(reader, SerializedRendererContainer.class);


			}



			@Override
			public void renderLightMap(Batch batch) {
				for (EscapyRenderable renderable: container)
					renderable.renderLightMap(batch);
			}

			@Override
			public void renderGraphics(Batch batch) {
				for (EscapyRenderable renderable: container)
					renderable.renderGraphics(batch);
			}

			@Override
			public void renderNormalsMap(Batch batch) {
				for (EscapyRenderable renderable: container)
					renderable.renderNormalsMap(batch);
			}
		};
	}
}

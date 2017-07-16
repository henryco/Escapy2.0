package net.irregular.escapy.engine.map.location.render;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyNamedArray;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;

/**
 * @author Henry on 16/07/17.
 */
public class RenderContainer implements EscapyRenderable {


	public final EscapyAssociatedArray<EscapyRenderable> container;

	public RenderContainer() {
		this.container = new EscapyNamedArray<>(EscapyRenderable.class);
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


}

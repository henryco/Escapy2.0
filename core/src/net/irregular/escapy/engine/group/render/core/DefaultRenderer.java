package net.irregular.escapy.engine.group.render.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.graphic.render.fbo.EscapyFBO;
import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;
import net.irregular.escapy.engine.graphic.render.program.gl10.mask.LightMask;

/**
 * @author Henry on 20/07/17.
 */
public class DefaultRenderer implements EscapyRenderer {


	private EscapyAssociatedArray<EscapyRenderable> renderGroups;
	private EscapyAssociatedArray<LightMask> lightMasks;


	private EscapyFBO[] fboRenerGroup;


	private Batch batch;

	@Override
	public void render(float delta) {

		for (int i = 0; i < renderGroups.size(); i++) {
			final int k = i;
			fboRenerGroup[i].begin(() -> {

				renderGroups.asArray()[k].renderGraphics(batch);
			});

		}

	}




	@Override
	public void resize(int width, int height) {

	}

}

package net.irregular.escapy.engine.group.render.loader.imp;

import net.irregular.escapy.engine.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.engine.group.render.core.EscapyRenderer;
import net.irregular.escapy.engine.group.render.loader.RendererLoader;

/**
 * @author Henry on 20/07/17.
 */
public class DefaultRendererLoader implements RendererLoader<EscapySubLocation> {



	@Override
	public EscapyRenderer loadRenderer(String path, EscapySubLocation arg) {




		return new EscapyRenderer() {

			@Override
			public void render(float delta) {

			}

			@Override
			public void resize(int width, int height) {

			}
		};
	}


}

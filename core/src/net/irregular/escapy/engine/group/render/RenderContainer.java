package net.irregular.escapy.engine.group.render;

import net.irregular.escapy.engine.env.utils.proxy.EscapyProxyListener;
import net.irregular.escapy.engine.group.map.core.location.EscapySubLocation;
import net.irregular.escapy.engine.group.render.core.EscapyRenderer;
import net.irregular.escapy.engine.group.render.loader.RendererLoader;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Henry on 19/07/17.
 */
public class RenderContainer {

	private final Map<String, String> rendererMap;
	private final RendererLoader<EscapySubLocation> rendererLoader;
	private final EscapyProxyListener proxyListener;

	private EscapyRenderer renderer;


	private final class RenderProxyListener implements EscapyProxyListener {

		@Override
		public void onProxyMethodInvoked(Object methodResult, String methodName) {
			if (methodResult != null && methodResult instanceof EscapySubLocation) {
				final EscapySubLocation subLocation = (EscapySubLocation) methodResult;
				final String parentName = subLocation.getParentLocation().getName();

				renderer = rendererLoader.loadRenderer("", subLocation);
			}
		}

	}



	public RenderContainer(RendererLoader<EscapySubLocation> rendererLoader,
						   Collection<Map.Entry<String, String>> renderers) {

		this.rendererLoader = rendererLoader;
		this.rendererMap = new HashMap<>();
		this.proxyListener = new RenderProxyListener();

		for (Map.Entry<String, String> r: renderers)
			rendererMap.put(r.getKey(), r.getValue());
	}




	public void render(float delta) {
		if (renderer != null) renderer.render(delta);
	}

	public void resize(int width, int height) {
		if (renderer != null) renderer.resize(width, height);
	}

	public EscapyProxyListener getProxyListener() {
		return proxyListener;
	}
}
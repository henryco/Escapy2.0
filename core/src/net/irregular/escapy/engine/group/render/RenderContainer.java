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
public class RenderContainer implements EscapyRenderer {

	private final Map<String, Map<String, String>> rendererMap;
	private final RendererLoader<EscapySubLocation> rendererLoader;
	private final EscapyProxyListener proxyListener;

	private EscapyRenderer renderer;


	public static final class TargetGroup {

		public final String name;
		public final String subName;
		public final String path;

		public TargetGroup(String name, String subName, String path) {
			this.name = name;
			this.subName = subName;
			this.path = path;
		}
	}


	private final class RenderProxyListener implements EscapyProxyListener {

		@Override
		public void onProxyMethodInvoked(Object methodResult, String methodName, Object[] args) {
			if (methodResult != null
					&& methodResult instanceof EscapySubLocation && methodName.equals("switchSubLocation"))
			{
				final EscapySubLocation subLocation = (EscapySubLocation) methodResult;
				final String parentName = subLocation.getParentLocation().getName();
				final String path = rendererMap.get(parentName).get(subLocation.getName());
				renderer = rendererLoader.loadRenderer(path, subLocation);
			}
		}
	}


	public EscapyProxyListener getProxyListener() {
		return proxyListener;
	}


	public RenderContainer(RendererLoader<EscapySubLocation> rendererLoader,
						   Collection<TargetGroup> renderers) {

		this.rendererLoader = rendererLoader;
		this.rendererMap = new HashMap<>();
		this.proxyListener = new RenderProxyListener();

		for (TargetGroup group: renderers) {
			Map<String, String> map = rendererMap.computeIfAbsent(group.name, k -> new HashMap<>());
			map.put(group.subName, group.path);
		}
	}



	@Override
	public <T> T getRendererAttribute(String name) {
		return renderer.getRendererAttribute(name);
	}

	@Override
	public void render(float delta) {
		if (renderer != null) {
			renderer.render(delta);
		}
	}

	@Override
	public void resize(int width, int height) {
		if (renderer != null) {
			renderer.resize(width, height);
		}
	}

	@Override
	public void dispose() {
		if (renderer != null) {
			renderer.dispose();
		}
	}

	@Override
	public String getName() {
		return renderer.getName();
	}

}
package net.tindersamurai.escapy.group.container.imp;

import net.tindersamurai.escapy.group.container.EscapyRendererContainer;
import net.tindersamurai.escapy.group.map.core.location.EscapySubLocation;
import net.tindersamurai.escapy.group.render.core.EscapyRenderer;
import net.tindersamurai.escapy.group.render.loader.RendererLoader;
import net.tindersamurai.escapy.utils.EscapyLogger;
import net.tindersamurai.escapy.utils.files.EscapyFiles;
import net.tindersamurai.escapy.utils.proxy.EscapyProxyListener;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Henry on 19/07/17.
 */
public class DefaultRendererContainer implements EscapyRendererContainer<EscapySubLocation> {

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
					&& methodResult instanceof EscapySubLocation
					&& methodName.equals("switchSubLocation")) {
				renderer = switchRenderer((EscapySubLocation) methodResult);
			}
		}
	}


	public EscapyProxyListener getProxyListener() {
		return proxyListener;
	}


	protected DefaultRendererContainer(RendererLoader<EscapySubLocation> rendererLoader,
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
	public EscapyRenderer switchRenderer(EscapySubLocation rendererSource) {

		final String parentName = rendererSource.getParentLocation().getName();
		final String path = rendererMap.get(parentName).get(rendererSource.getName());

		try {
			this.renderer = rendererLoader.loadRenderer(EscapyFiles.safetyPath(path), rendererSource);
			return this.renderer;
		} catch (Exception e) {
			new EscapyLogger("RenderContainer").fileJava().log(e, true);
		}
		return null;
	}

	@Override
	public <T> T getRendererAttribute(String name) {
		if (renderer != null)
			return renderer.getRendererAttribute(name);
		return null;
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
		if (renderer != null)
			return renderer.getName();
		return null;
	}

}
package net.irregular.escapy.engine.map.zloader;

import net.irregular.escapy.engine.graphic.render.mapping.EscapyRenderable;

/**
 * @author Henry on 16/07/17.
 */
public interface RenderContainerLoader<T> extends LoaderUtils {

	EscapyRenderable loadRenderContainer(String path, T arg);
}

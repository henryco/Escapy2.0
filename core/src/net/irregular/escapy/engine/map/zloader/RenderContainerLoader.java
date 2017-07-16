package net.irregular.escapy.engine.map.zloader;

import net.irregular.escapy.engine.map.location.render.RenderContainer;

/**
 * @author Henry on 16/07/17.
 */
public interface RenderContainerLoader<T> extends LoaderUtils {

	RenderContainer loadRenderContainer(T arg);
}

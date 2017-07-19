package net.irregular.escapy.engine.group.render.loader;

import net.irregular.escapy.engine.env.utils.loader.EscapyLoaderUtils;
import net.irregular.escapy.engine.group.render.core.EscapyRenderer;

/**
 * @author Henry on 20/07/17.
 */
public interface RendererLoader extends EscapyLoaderUtils {

	EscapyRenderer loadRenderer(String path);
}

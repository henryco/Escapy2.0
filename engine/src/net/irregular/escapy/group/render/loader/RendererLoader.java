package net.irregular.escapy.group.render.loader;

import net.irregular.escapy.environment.utils.loader.EscapyLoaderUtils;
import net.irregular.escapy.group.render.core.EscapyRenderer;

/**
 * @author Henry on 20/07/17.
 */
public interface RendererLoader<ARGUMENT_TYPE> extends EscapyLoaderUtils {

	EscapyRenderer loadRenderer(String path, ARGUMENT_TYPE arg);
}

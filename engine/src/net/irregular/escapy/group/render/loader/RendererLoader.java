package net.irregular.escapy.group.render.loader;

import net.irregular.escapy.group.render.core.EscapyRenderer;
import net.irregular.escapy.utils.loader.EscapyLoaderUtils;

/**
 * @author Henry on 20/07/17.
 */
public interface RendererLoader<ARGUMENT_TYPE> extends EscapyLoaderUtils {

	EscapyRenderer loadRenderer(String path, ARGUMENT_TYPE arg);
}

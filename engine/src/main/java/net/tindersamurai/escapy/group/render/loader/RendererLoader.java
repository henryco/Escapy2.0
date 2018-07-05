package net.tindersamurai.escapy.group.render.loader;

import net.tindersamurai.escapy.group.render.core.EscapyRenderer;
import net.tindersamurai.escapy.utils.loader.EscapyLoaderUtils;

/**
 * @author Henry on 20/07/17.
 */
public interface RendererLoader<ARGUMENT_TYPE> extends EscapyLoaderUtils {

	EscapyRenderer loadRenderer(String path, ARGUMENT_TYPE arg);
}

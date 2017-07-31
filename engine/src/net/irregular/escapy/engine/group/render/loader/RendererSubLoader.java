package net.irregular.escapy.engine.group.render.loader;

import net.irregular.escapy.engine.env.utils.arrContainer.EscapyAssociatedArray;
import net.irregular.escapy.engine.env.utils.loader.EscapyLoaderUtils;
import net.irregular.escapy.engine.env.utils.serial.EscapySerialized;

/**
 * @author Henry on 25/07/17.
 */
public interface RendererSubLoader<SUB_RENDERER, SERIALIZED_SOURCE extends EscapySerialized, SUB_PART> extends EscapyLoaderUtils {


	EscapyAssociatedArray<SUB_RENDERER> loadRendererPart(
			SERIALIZED_SOURCE serialized, SUB_PART subPart
	);

	default EscapyAssociatedArray<SUB_RENDERER> loadRendererPart(SERIALIZED_SOURCE serialized) {
		return this.loadRendererPart(serialized, null);
	}

}
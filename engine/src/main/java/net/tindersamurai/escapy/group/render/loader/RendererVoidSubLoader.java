package net.tindersamurai.escapy.group.render.loader;

import net.tindersamurai.escapy.utils.array.EscapyAssociatedArray;
import net.tindersamurai.escapy.utils.serial.EscapySerialized;

/**
 * @author Henry on 25/07/17.
 */
public interface RendererVoidSubLoader<SUB_RENDERER, SERIALIZED_SOURCE extends EscapySerialized>
		extends RendererSubLoader<SUB_RENDERER, SERIALIZED_SOURCE, Void> {


	EscapyAssociatedArray<SUB_RENDERER> loadRendererPart(SERIALIZED_SOURCE serialized);

	default EscapyAssociatedArray<SUB_RENDERER> loadRendererPart(SERIALIZED_SOURCE serialized, Void subPart) {
		return this.loadRendererPart(serialized);
	}
}
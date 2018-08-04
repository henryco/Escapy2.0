package net.tindersamurai.escapy.components.control.plain;

import lombok.NoArgsConstructor;

import static net.tindersamurai.escapy.control.IEscapyControllerListener.*;

@NoArgsConstructor
public abstract class CoreCharacterListener<T> extends CoreListener<T>
		implements MoveLeftListener, MoveRightListener, RunListener, SitListener, InteractListener {

	@SafeVarargs
	public CoreCharacterListener(T... userData) {
		super(userData);
	}
}
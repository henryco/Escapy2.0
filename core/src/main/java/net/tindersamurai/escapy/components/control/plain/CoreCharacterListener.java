package net.tindersamurai.escapy.components.control.plain;

import static net.tindersamurai.escapy.control.IEscapyControllerListener.*;

public abstract class CoreCharacterListener<T> extends CoreListener<T>
		implements MoveLeftListener, MoveRightListener, RunListener, SitListener, InteractListener {

}
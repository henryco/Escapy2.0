package net.tindersamurai.escapy.control;

import com.badlogic.gdx.utils.Disposable;

public interface IEscapyControllerListener extends Disposable {

	default void addUserData(Object data) { }
	default  void removeUserData(Object data) { }

	default void onUpdate(float delta) { /* OPTIONAL */ }

	interface InteractListener extends IEscapyControllerListener { void onInteract(); }
	interface MoveLeftListener extends IEscapyControllerListener { void onMoveLeft(); }
	interface MoveRightListener extends IEscapyControllerListener { void onMoveRight(); }
	interface RunListener extends IEscapyControllerListener { void onRun(); }
	interface SitListener extends IEscapyControllerListener { void onSit(); }

	// todo more
}

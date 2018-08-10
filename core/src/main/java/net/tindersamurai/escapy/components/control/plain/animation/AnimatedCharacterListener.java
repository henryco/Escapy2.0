package net.tindersamurai.escapy.components.control.plain.animation;

import com.badlogic.gdx.graphics.g2d.Sprite;
import net.tindersamurai.escapy.control.IEscapyControllerListener.*;

public class AnimatedCharacterListener implements IAnimationListener,
		MoveLeftListener, MoveRightListener, RunListener,
		SitListener, InteractListener {

	@Override
	public void dispose() {

	}

	@Override
	public Sprite getDiffuseSprite() {
		return null;
	}

	@Override
	public Sprite getNormalsSprite() {
		return null;
	}

	@Override
	public Sprite getShadowsSprite() {
		return null;
	}

	@Override
	public void onInteract() {

	}

	@Override
	public void onUpdate() {

	}

	@Override
	public void onMoveLeft() {

	}

	@Override
	public void onMoveRight() {

	}

	@Override
	public void onRun() {

	}

	@Override
	public void onSit() {

	}
}
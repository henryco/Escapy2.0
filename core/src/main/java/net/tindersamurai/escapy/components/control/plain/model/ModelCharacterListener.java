package net.tindersamurai.escapy.components.control.plain.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.Setter;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.control.plain.CoreCharacterListener;

@Log
public class ModelCharacterListener
		extends CoreCharacterListener<Object>
		implements IModelListener {

	/**
	 * time in ms
	 */
	private @Setter float frameStep;


	@Override
	public void dispose() {

	}

	@Override
	protected Class<Object> getDataType() {
		return Object.class;
	}

	@Override
	public void onUpdate(float delta) {

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
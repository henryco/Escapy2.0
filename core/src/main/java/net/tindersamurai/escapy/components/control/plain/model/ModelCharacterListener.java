package net.tindersamurai.escapy.components.control.plain.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.graphic.animation.IEscapyAnimationSM;
import net.tindersamurai.escapy.components.control.plain.CoreCharacterListener;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;

import java.util.function.Consumer;

@Log
public class ModelCharacterListener
		extends CoreCharacterListener<Object>
		implements IModelListener {

	private final IEscapyAnimationSM animationSM;

	public ModelCharacterListener(IEscapyAnimationSM animationSM) {
		this.animationSM = animationSM;
	}


	@Override
	public void dispose() {

	}

	@Override
	protected Class<Object> getDataType() {
		return Object.class;
	}

	@Override
	public void applyToAll(Consumer<Sprite> spriteConsumer) {
		animationSM.applyToAllStateSprites(spriteConsumer);
	}

	@Override
	public void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) {
		animationSM.renderNormalMap(camera, batch, delta);
	}

	@Override
	public void renderShadowMap(IEscapyCamera camera, Batch batch, float delta) {
		animationSM.renderShadowMap(camera, batch, delta);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		animationSM.renderDiffuseMap(camera, batch, delta);
	}

	@Override
	public void onUpdate(float delta) {
		animationSM.update(delta);
	}

	@Override
	public Sprite getDiffuseSprite() {
		return animationSM.getCurrentSubState().getSprites().getDiffuse();
	}

	@Override
	public Sprite getNormalsSprite() {
		return animationSM.getCurrentSubState().getSprites().getNormal();
	}

	@Override
	public Sprite getShadowsSprite() {
		return animationSM.getCurrentSubState().getSprites().getShadow();
	}

	@Override
	public void onInteract() {
		animationSM.setState("interact");
	}

	@Override
	public void onMoveLeft() {
		animationSM.setState("left");
	}

	@Override
	public void onMoveRight() {
		animationSM.setState("right");
	}

	@Override
	public void onRun() {
		animationSM.setState("run");
	}

	@Override
	public void onSit() {
		animationSM.setState("sit");
	}
}
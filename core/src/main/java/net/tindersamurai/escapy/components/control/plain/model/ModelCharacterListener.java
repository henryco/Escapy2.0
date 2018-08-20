package net.tindersamurai.escapy.components.control.plain.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.graphic.animation.IEscapyAnimationSM;
import net.tindersamurai.escapy.components.control.plain.CoreCharacterListener;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.utils.collections.EscapyFixedSizeConcurrentLinkedQueue;

import java.util.Queue;
import java.util.function.Consumer;

@Log
public class ModelCharacterListener
		extends CoreCharacterListener<Object>
		implements IModelListener {

	private static final String POSTFIX_LEFT = "-left";
	private static final String POSTFIX_RIGHT = "-right";
	private static final String POSTFIX_DEFAULT = POSTFIX_RIGHT;

	private static final int LFT = 0;
	private static final int RGT = 1;
	private static final int SIT = 2;
	private static final int RUN = 3;
	private static final int MOV = 4;
	private static final int ACT = 5;

	private boolean[] state;
	private boolean[] last;

	private final IEscapyAnimationSM animationSM;

	private Consumer<Sprite> spriteConsumer;

	public ModelCharacterListener(IEscapyAnimationSM animationSM) {
		this.animationSM = animationSM;
		this.state = new boolean[6];
		this.last = new boolean[2];
		resetState();
	}

	private void resetState() {
		for (int i = 0; i < this.state.length; i++)
			state[i] = false;
	}

	@Override
	public void dispose() {

	}

	@Override
	protected Class<Object> getDataType() {
		return Object.class;
	}

	@Override
	public void apply(Consumer<Sprite> spriteConsumer) {
		this.spriteConsumer = spriteConsumer;
	}

	@Override
	public void applyToAll(Consumer<Sprite> spriteConsumer) {
		animationSM.applyToAllStates(spriteConsumer);
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
	public Sprite getDiffuseSprite() {
		val diffuse = animationSM.getCurrentSubState().getSprites().getDiffuse();
		if (spriteConsumer != null && diffuse != null)
			spriteConsumer.accept(diffuse);
		return diffuse;
	}

	@Override
	public Sprite getNormalsSprite() {
		val normal = animationSM.getCurrentSubState().getSprites().getNormal();
		if (spriteConsumer != null && normal != null)
			spriteConsumer.accept(normal);
		return normal;
	}

	@Override
	public Sprite getShadowsSprite() {
		val shadow = animationSM.getCurrentSubState().getSprites().getShadow();
		if (spriteConsumer != null && shadow != null)
			spriteConsumer.accept(shadow);
		return shadow;
	}

	private void updState(boolean left, boolean right, String prefix) {
		if (right) animationSM.setState(prefix + POSTFIX_RIGHT);
		else if (left) animationSM.setState(prefix + POSTFIX_LEFT);
		else animationSM.setState(prefix + POSTFIX_DEFAULT);
	}

	@Override // FIXME
	public void onUpdate(float delta) {

		// MOVE ANIMATION
		if (state[MOV]) {
			if (state[SIT] && state[RUN])
				updState(state[LFT], state[RGT], "sit-run");
			else if (state[SIT])
				updState(state[LFT], state[RGT], "sit-move");
			else if (state[RUN])
				updState(state[LFT], state[RGT], "run");
			else
				updState(state[LFT], state[RGT], "move");
		}

		// STATIC ANIMATION
		else {
			if (state[SIT] && state[ACT])
				updState(last[LFT], last[RGT], "sit-interact");
			else if (state[SIT])
				updState(last[LFT], last[RGT], "sit");
			else if (state[ACT])
				updState(last[LFT], last[RGT], "interact");
			else
				updState(last[LFT], last[RGT], "idle");
		}

		animationSM.update(delta);
		resetState();
	}

	@Override
	public void onInteract() {
		state[ACT] = true;
	}

	@Override
	public void onMoveLeft() {
		state[LFT] = true;
		state[MOV] = true;
		last[LFT] = true;
		last[RGT] = false;
	}

	@Override
	public void onMoveRight() {
		state[RGT] = true;
		state[MOV] = true;
		last[LFT] = false;
		last[RGT] = true;
	}

	@Override
	public void onRun() {
		state[RUN] = true;
	}

	@Override
	public void onSit() {
		state[SIT] = true;
	}

}
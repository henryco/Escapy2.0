package net.tindersamurai.escapy.graphic.animation;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.Getter;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.utils.map.EscapyMultiKey;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * State machine, very useful
 * in animations
 *
 * @author henryco
 */
@Log
public class EscapyAnimationSM implements IEscapyAnimationSM {

	private final Map<String, State> states;
	private final AtomicBoolean available;
	private final float frameStepMs;

	private @Getter
	State currentState;
	private @Getter
	SubState currentSubState;
	private @Getter
	Animation currentAnimation;

	private State nextState;
	private SubState nextSubState;
	private Animation nextAnimation;

	private float accumulator;

	public EscapyAnimationSM(float frameStepMs) {
		this.available = new AtomicBoolean(false);
		this.states = new HashMap<>();
		this.frameStepMs = frameStepMs;
		this.accumulator = 0;
	}

	@Override
	public void update(float delta) {
		if (!available.get()) return;

		accumulator += delta;
		while (accumulator >= frameStepMs) {
			accumulator -= frameStepMs;

			val next = currentSubState.getNext();
			if (next != null) {
				currentSubState = next;

				val render = currentSubState.getRenderable();
				if (render != null)
					render.start();

				continue;
			}

			// next == null and we have next state
			if (nextState != null) {
				currentState = nextState;
				currentSubState = nextSubState;
				currentAnimation = nextAnimation;
				nextState = null;
				nextSubState = null;
				nextAnimation = null;
			} else {
				val animation = rollAnimation(currentState.getAnimations());
				currentSubState = animation.getSub();
				currentAnimation = animation;
			}
		}
	}

	@Override
	public void setState(String name, boolean initial) {
		if (!initial) {
			setState(name);
			return;
		}

		available.set(false);
		{
			currentState = states.get(name);
			val animation = rollAnimation(currentState.getAnimations());
			currentSubState = animation.getSub();
			currentAnimation = animation;
			accumulator = 0;
			nextState = null;
			nextSubState = null;
			nextAnimation = null;
		}
		available.set(true);
	}

	@Override
	public void setState(String name) {
		if (currentState != null
				&& name.equals(currentState.getName())) {
			return;
		}

		available.set(false);
		{
			accumulator = 0;

			val trans = currentState.getTrans();
			val next = states.get(name);

			if (next == null) {
				log.warning("Unknown animation state: " + name);
				available.set(true);
				return;
			}

			val __nextAnimation = rollAnimation(next.getAnimations());

			if (trans != null) {

				String key1 = currentAnimation.getName();
				String key2 = __nextAnimation.getName();

				val transition = trans.get(new EscapyMultiKey<>(key1, key2));

				// if there are transitions present
				if (transition != null) {
					val animation = rollAnimation(transition.getAnimations());

					currentAnimation = animation;
					currentSubState = animation.getSub();
					currentState = transition;

					nextAnimation = __nextAnimation;
					nextSubState = __nextAnimation.getSub();
					nextState = next;

					available.set(true);
					return;
				}
			}

			// if there are no transitions

			currentSubState = __nextAnimation.getSub();
			currentAnimation = __nextAnimation;
			currentState = next;
			nextState = null;
			nextSubState = null;
			nextAnimation = null;
		}
		available.set(true);
	}

	private static Animation rollAnimation(
			Animation[] alternatives
	) {
		if (alternatives == null || alternatives.length == 0)
			return null;
		if (alternatives.length == 1)
			return alternatives[0];

		val r = new Random().nextFloat();
		float d = 0f;

		for (val a : alternatives) {
			if (r >= d && r <= (d = Math.min(1, d + a.getProbability())))
				return a;
		}

		log.warning("----------------------------------------");
		log.warning("ROLL RANDOM ANIMATION CALCULATION ERROR!");
		log.warning(Arrays.toString(alternatives));
		log.warning("----------------------------------------");

		return alternatives[0];
	}

	@Override
	public void addState(State state) {
		states.put(state.getName(), state);
	}

	@Override
	public void renderNormalMap(IEscapyCamera camera, Batch batch, float delta) {
		if (currentSubState == null) return;
		val renderable = currentSubState.getRenderable();
		if (renderable != null) renderable.renderNormalMap(camera, batch, delta);
	}

	@Override
	public void renderShadowMap(IEscapyCamera camera, Batch batch, float delta) {
		if (currentSubState == null) return;
		val renderable = currentSubState.getRenderable();
		if (renderable != null) renderable.renderShadowMap(camera, batch, delta);
	}

	@Override
	public void renderDiffuseMap(IEscapyCamera camera, Batch batch, float delta) {
		if (currentSubState == null) return;
		val renderable = currentSubState.getRenderable();
		if (renderable != null) renderable.renderDiffuseMap(camera, batch, delta);
	}

	@Override
	public void applyToAllStates(Consumer<Sprite> consumer) {
		for (State state : states.values()) {
			consumeState(state, consumer);
		}
	}

}
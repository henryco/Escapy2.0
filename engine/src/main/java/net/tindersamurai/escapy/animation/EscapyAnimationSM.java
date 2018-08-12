package net.tindersamurai.escapy.animation;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.Getter;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * State machine, very useful
 * in animations
 * @author henryco
 */ @Log
public class EscapyAnimationSM implements IEscapyAnimationSM {

	private final Map<String, State> states;
	private final AtomicBoolean available;
	private final float frameStepMs;

	private @Getter State currentState;
	private @Getter SubState currentSubState;

	private State nextState;
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
				nextState = null;
			}

			// get sub state of current state
			currentSubState = rollSubState(currentState.getAlt());
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
			currentSubState = rollSubState(currentState.getAlt());
			accumulator = 0;
			nextState = null;
		}
		available.set(true);
	}

	@Override
	public void setState(String name) {
		available.set(false);
		{
			accumulator = 0;

			val trans = currentState.getTrans();
			val next = states.get(name);

			if (trans != null) {
				val transition = trans.get(name);

				// if there are transitions present
				if (transition != null) {
					currentSubState = rollSubState(transition.getAlt());
					currentState = transition;
					nextState = next;
					return;
				}
			}

			// if there are no transitions
			currentSubState = rollSubState(next.getAlt());
			currentState = next;
			nextState = null;
		}
		available.set(true);
	}

	private static SubState rollSubState (
			Alternative[] alternatives
	) {
		if (alternatives == null || alternatives.length == 0)
			return null;
		if (alternatives.length == 1)
			return alternatives[0].getSub();

		val r = new Random().nextFloat();
		float d = 0f;

		for (val a : alternatives) {
			if (r >= d && r <= (d = Math.min(1, d + a.getProbability())))
				return a.getSub();
		}

		log.warning("---------------------------------------");
		log.warning("GET RANDOM SUB STATE CALCULATION ERROR!");
		log.warning(Arrays.toString(alternatives));
		log.warning("---------------------------------------");

		return alternatives[0].getSub();
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
	public void applyToAllStateSprites(Consumer<Sprite> consumer) {
		states.values().forEach(state -> consumeState(state, consumer));
	}


	private static void consumeState (
			final State state, final Consumer<Sprite> consumer
	) {
		final Map<String, State> trans = state.getTrans();
		if (trans != null) {
			final Collection<State> values = trans.values();
			values.forEach(ss -> consumeState(ss, consumer));
		}

		final Alternative[] alt = state.getAlt();
		if (alt == null) return;

		for (Alternative a : alt) {
			final Sprites s = a.getSub().getSprites();
			consumer.accept(s.getDiffuse());
			consumer.accept(s.getNormal());
			consumer.accept(s.getShadow());
		}
	}

 }
package net.tindersamurai.escapy.graphic.animation;

import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import net.tindersamurai.escapy.graphic.IEscapyRenderable;
import net.tindersamurai.escapy.utils.map.EscapyMultiKey;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

public interface IEscapyAnimationSM extends IEscapyRenderable{

	interface AnimationRender extends IEscapyRenderable {
		void start();
		void stop();
		boolean isFinished();
		default void restart() {
			stop();
			start();
		}
	}

	@Value final class Sprites {
		private Sprite diffuse;
		private Sprite normal;
		private Sprite shadow;
	}

	@Data @AllArgsConstructor @NoArgsConstructor
	final class SubState {
		private AnimationRender renderable;
		private Sprites sprites;
		private SubState next;
		private SubState root;

		public void setRoot(SubState root) {
			SubState curr = this;
			while (curr != null) {
				curr.root = root;
				curr = curr.next;
			}
		}

		@Override
		public String toString() {
			return "SubState{" +
					"renderable=" + renderable +
					", sprites=" + sprites +
					", next=" + next +
					'}';
		}
	}

	@Value final class State {
		// STATE:ANIMATION -> NEXT:ANIMATION
		private Map<EscapyMultiKey<String, String>, State> trans;
		private Animation[] animations;
		private String name;
	}

	@Value final class Animation {
		private float probability;
		private SubState sub;
		private String name;
	}

	void update(float delta);

	void setState(String name, boolean initial);

	void setState(String name);

	void addState(State state);

	State getCurrentState();

	SubState getCurrentSubState();

	Animation getCurrentAnimation();

	void applyToAllStates(Consumer<Sprite> consumer);

	default void consumeState (
			final State state, final Consumer<Sprite> consumer
	) {
		final Map<EscapyMultiKey<String, String>, State> trans = state.getTrans();
		if (trans != null) {
			final Collection<State> values = trans.values();
			values.forEach(ss -> consumeState(ss, consumer));
		}

		final Animation[] alt = state.getAnimations();
		if (alt == null) return;

		for (Animation a : alt)
			consumeSubState(a.getSub(), consumer);
	}

	default void consumeSubState (
			final SubState subState,
			final Consumer<Sprite> consumer
	) {
		SubState sub = subState.getRoot();

		while (sub != null) {
			final Sprites s = sub.getSprites();

			final Sprite diffuse = s.getDiffuse();
			if (diffuse != null) consumer.accept(diffuse);

			final Sprite normal = s.getDiffuse();
			if (normal != null) consumer.accept(normal);

			final Sprite shadow = s.getDiffuse();
			if (shadow != null) consumer.accept(shadow);

			sub = sub.getNext();
		}
	}

}
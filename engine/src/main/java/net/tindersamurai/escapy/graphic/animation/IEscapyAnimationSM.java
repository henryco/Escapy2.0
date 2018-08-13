package net.tindersamurai.escapy.graphic.animation;

import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.Value;
import net.tindersamurai.escapy.graphic.IEscapyRenderable;

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

	@Value final class SubState {
		private AnimationRender renderable;
		private Sprites sprites;
		private SubState next;
	}

	@Value final class State {
		private Map<String, State> trans;
		private Alternative[] alt;
		private String name;
	}

	@Value final class Alternative {
		private float probability;
		private SubState sub;
	}

	void update(float delta);

	void setState(String name, boolean initial);

	void setState(String name);

	void addState(State state);

	State getCurrentState();

	SubState getCurrentSubState();

	void applyToAllStateSprites(Consumer<Sprite> consumer);
}
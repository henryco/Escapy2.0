package net.tindersamurai.escapy.components.model;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.activecomponent.comp.annotation.NotNull;
import net.tindersamurai.escapy.graphic.animation.EscapyAnimationSM;
import net.tindersamurai.escapy.graphic.animation.IEscapyAnimationSM;
import net.tindersamurai.escapy.graphic.animation.IEscapyAnimationSM.*;
import net.tindersamurai.escapy.utils.files.EscapyFiles;

import java.util.Map;

@Provide
@EscapyComponentFactory("animation")
public class AnimationFactory {


	@EscapyComponent("sprites")
	public final Sprites sprites (
			@Arg("diffuse") String diffuse,
			@Arg("normal") String normal,
			@Arg("shadow") String shadow
	) {
		return new Sprites(
				EscapyFiles.loadSprite(diffuse),
				EscapyFiles.loadSprite(normal),
				EscapyFiles.loadSprite(shadow)
		);
	}


	@EscapyComponent("state")
	public final State state(
			@Arg("name") @NotNull String name,
			@Arg("transitions") Map<String, State> transitions,
			@Arg("animations") Animation ... animations
	) {
		return new State(
				transitions,
				animations,
				name
		);
	}


	@EscapyComponent("substate")
	public final SubState subState (
			@NotNull @Arg("sprites") Sprites sprites,
			@Arg("render") AnimationRender render,
			@Arg("next") SubState ... subStates
	) {
		return new SubState (
				render, sprites, nestSubStates(subStates)
		);
	}


	@EscapyComponent("animation")
	public final Animation animation (
			@Arg("probability") Float probability,
			@Arg("substates") SubState ... subStates
	) {
		return new Animation(
				probability == null ? 1f : probability,
				nestSubStates(subStates)
		);
	}


	@EscapyComponent("config")
	public final IEscapyAnimationSM smConfig (
			@Arg("speed") @NotNull Float speed,
			@Arg("initial") String initialState,
			@Arg("states") State... states
	) {

		IEscapyAnimationSM sm = new EscapyAnimationSM(1000f / speed);
		for (State state : states)
			sm.addState(state);

		if (initialState != null) sm.setState(initialState, true);
		else sm.setState(states[0].getName(), true);

		return sm;
	}


	private static SubState nestSubStates (
			SubState ... subStates
	) {

		SubState root = null;
		SubState head = null;

		if (subStates.length > 0) {
			root = subStates[0];
			head = root;
		}

		for (int i = 1; i < subStates.length; i++) {
			val sub = subStates[i];
			head.setNext(sub);
			head = sub;
		}
		return root;
	}

}
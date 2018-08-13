package net.tindersamurai.escapy.components.model;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.activecomponent.comp.annotation.NotNull;
import net.tindersamurai.escapy.graphic.animation.EscapyAnimationSM;
import net.tindersamurai.escapy.graphic.animation.IEscapyAnimationSM;
import net.tindersamurai.escapy.graphic.animation.IEscapyAnimationSM.Sprites;
import net.tindersamurai.escapy.graphic.animation.IEscapyAnimationSM.State;
import net.tindersamurai.escapy.utils.files.EscapyFiles;

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
			@Arg("name") @NotNull String name
	) {

		return new State(
				null,
				null,
				name
		);
	}

	@EscapyComponent("config")
	public final IEscapyAnimationSM smConfig (
			@Arg("speed") @NotNull Float speed,
			@Arg("initial") String initialState,
			@Arg("states") State... states
	) {

		IEscapyAnimationSM sm = new EscapyAnimationSM(speed);
		for (State state : states)
			sm.addState(state);

		if (initialState != null) sm.setState(initialState, true);
		else sm.setState(states[0].getName(), true);

		return sm;
	}


}
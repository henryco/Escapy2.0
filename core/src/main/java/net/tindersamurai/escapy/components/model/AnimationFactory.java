package net.tindersamurai.escapy.components.model;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.escapy.graphic.animation.EscapyAnimationSM;
import net.tindersamurai.escapy.graphic.animation.IEscapyAnimationSM;
import net.tindersamurai.escapy.graphic.animation.IEscapyAnimationSM.State;

@Provide
@EscapyComponentFactory("animation")
public class AnimationFactory {


	@EscapyComponentFactory("state")
	public static final class StateFactory {


	}


	@EscapyComponent("config")
	public final IEscapyAnimationSM smConfig (
			@Arg("speed") Float speed,
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
package net.tindersamurai.escapy.components.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.activecomponent.comp.annotation.NotNull;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.graphic.animation.EscapyAnimationSM;
import net.tindersamurai.escapy.graphic.animation.IEscapyAnimationSM;
import net.tindersamurai.escapy.graphic.animation.IEscapyAnimationSM.*;
import net.tindersamurai.escapy.utils.files.EscapyFiles;

import javax.inject.Inject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Provide
@EscapyComponentFactory("animations")
public class AnimationFactory {

	private final EscapyGameContext context;

	@Inject
	public AnimationFactory(EscapyGameContext context) {
		this.context = context;
	}

	@EscapyComponent("sprites")
	public final Sprites sprites (
			@Arg("diffuse") String diffuse,
			@Arg("normal") String normal,
			@Arg("shadow") String shadow
	) {
		val root = context.getConfigsFilePath();

		Sprite diff = null;
		Sprite norm = null;
		Sprite shad = null;

		if (diffuse != null)
			diff = EscapyFiles.loadSprite(Files.exists(Paths.get(diffuse)) ? diffuse : root + diffuse);
		if (normal != null)
			norm = EscapyFiles.loadSprite(Files.exists(Paths.get(normal)) ? normal : root + normal);
		if (shadow != null)
			shad = EscapyFiles.loadSprite(Files.exists(Paths.get(shadow)) ? shadow : root + shadow);

		return new Sprites(diff, norm, shad);
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
		SubState subState = new SubState();
		subState.setRenderable(render);
		subState.setSprites(sprites);
		subState.setRoot(subState);

		final SubState nested = nestSubStates(subStates);
		if (nested != null) {
			nested.setRoot(subState);
			subState.setNext(nested);
		}

		return subState;
	}


	@EscapyComponent("animation")
	public final Animation animation (
			@NotNull @Arg("name") String name,
			@Arg("probability") Float probability,
			@Arg("substates") SubState ... subStates
	) {
		return new Animation(
				probability == null ? 1f : probability,
				nestSubStates(subStates),
				name
		);
	}


	@EscapyComponent("config")
	public final IEscapyAnimationSM smConfig (
			@Arg("speed") @NotNull Float speed,
			@Arg("initial") String initialState,
			@Arg("states") State... states
	) {

		IEscapyAnimationSM sm = new EscapyAnimationSM(speed / 1000f);
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

		if (root != null)
			root.setRoot(root);

		return root;
	}

}
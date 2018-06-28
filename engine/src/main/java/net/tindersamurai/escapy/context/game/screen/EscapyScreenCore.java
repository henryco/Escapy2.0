package net.tindersamurai.escapy.context.game.screen;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;

public abstract class EscapyScreenCore implements EscapyScreen {

	private @Delegate @Setter @Getter
	EscapyScreenContext screenContext;

	@Override
	public void resize(int width, int height) {
		/* VOID */
	}
}
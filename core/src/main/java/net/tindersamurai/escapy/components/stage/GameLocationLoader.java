package net.tindersamurai.escapy.components.stage;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.escapy.components.stage.plain.LocationLoader;
import net.tindersamurai.escapy.components.stage.plain.LocationSwitcher;
import net.tindersamurai.escapy.map.location.IEscapyLocationHandler;

import javax.inject.Inject;

@Provide
public class GameLocationLoader implements LocationLoader {

	private final IEscapyLocationHandler handler;
	private final LocationSwitcher switcher;

	@Inject public GameLocationLoader (
			IEscapyLocationHandler handler,
			LocationSwitcher switcher
	) {
		this.switcher = switcher;
		this.handler = handler;
	}

	@Override
	public boolean loadLocation() {
		val data = switcher.getLocationMetaData();
		return handler.switchLocation(data.getFile().getUrl());
	}
}
package net.tindersamurai.escapy.components.stage;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.escapy.components.stage.plain.LocationLoader;
import net.tindersamurai.escapy.components.stage.plain.LocationSetter;
import net.tindersamurai.escapy.map.location.IEscapyLocationHandler;

import javax.inject.Inject;

@Provide
public class GameLocationLoader implements LocationLoader {

	private final IEscapyLocationHandler handler;
	private final LocationSetter setter;

	@Inject public GameLocationLoader (
			IEscapyLocationHandler handler,
			LocationSetter setter
	) {
		this.handler = handler;
		this.setter = setter;
	}

	@Override
	public boolean loadLocation() {
		val data = setter.getLocationMetaData();
		return handler.switchLocation(data.getFile().getUrl());
	}
}
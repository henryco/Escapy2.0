package net.tindersamurai.escapy.components.map;

import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;

import javax.inject.Singleton;

@Provide @Singleton
@EscapyComponentFactory("escapy")
public final class MapFactory {

	@EscapyComponent("map")
	public void map() {

	}

}
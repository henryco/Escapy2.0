package net.tindersamurai.activecomponent.core;

import lombok.extern.java.Log;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.activecomponent.comp.annotation.NotNull;

import java.util.HashMap;
import java.util.Map;

@Log @EscapyComponentFactory("v")
public final class VariablesCoreComponent {

	private final Map<Object, Object> variables;

	public VariablesCoreComponent() {
		this.variables = new HashMap<>();
	}

	@EscapyComponent("store")
	public final <T> T store (
			@NotNull @Arg("name") String name,
			@NotNull @Arg("arg") T arg
	) {
		return arg;
	}

	@EscapyComponent("get")
	public final <T> T get (
			@NotNull @Arg("name") String name
	) {
		//noinspection unchecked
		return (T) variables.get(name);
	}

}
package net.tindersamurai.activecomponent.core;

import lombok.extern.java.Log;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.activecomponent.comp.annotation.NotNull;
import net.tindersamurai.activecomponent.comp.factory.EscapyComponentFactoryProvider;
import net.tindersamurai.activecomponent.comp.factory.IEscapyComponentFactory;

import java.util.HashMap;
import java.util.Map;

@Log @EscapyComponentFactory("v")
public final class VariablesCoreComponent implements EscapyComponentFactoryProvider {

	private IEscapyComponentFactory componentFactory;
	private final Map<Object, Object> variables;

	public VariablesCoreComponent() {
		this.variables = new HashMap<>();
	}

	@Override
	public void provideComponentFactory(IEscapyComponentFactory factory) {
		this.componentFactory = factory;
	}

	@EscapyComponent("store")
	public final <T> T store (
			@NotNull @Arg("name") String name,
			@NotNull @Arg("arg") T arg
	) {
		variables.put(name, arg);
		return arg;
	}

	@EscapyComponent("get")
	public final <T> T get(@NotNull String name) {
		//noinspection unchecked
		return (T) variables.get(name);
	}

	@EscapyComponent("string")
	public final String string(@NotNull String arg) {
		// ${resources.config-path}/animations/mco.eacxml

		return arg;
	}
}
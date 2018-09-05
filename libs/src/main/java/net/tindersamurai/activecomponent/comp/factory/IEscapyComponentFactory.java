package net.tindersamurai.activecomponent.comp.factory;

import java.util.Map;

public interface IEscapyComponentFactory {

	<T> T createComponent(String name, Map<String, Object> arguments);

	String getNameSpaceSeparator();

	Object getFactory(String name);
}
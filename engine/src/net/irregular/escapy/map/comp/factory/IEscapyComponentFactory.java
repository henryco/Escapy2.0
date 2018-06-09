package net.irregular.escapy.map.comp.factory;

import java.util.Map;

public interface IEscapyComponentFactory {

	<T> T crateComponent(String name, Map<String, Object> arguments);
}
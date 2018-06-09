package net.irregular.escapy.map.data.comp.factory;

import java.util.Map;

public interface IEscapyComponentFactory {

	<T> T createComponent(String name, Map<String, Object> arguments);
}
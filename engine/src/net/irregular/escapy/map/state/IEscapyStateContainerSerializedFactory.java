package net.irregular.escapy.map.state;

import net.irregular.escapy.map.comp.factory.IEscapyComponentFactory;

public interface IEscapyStateContainerSerializedFactory {

	IEscapyStateContainer load(String file);

	void addComponentFactory(IEscapyComponentFactory factory);
}
package net.irregular.escapy.map.comp;

/**
 * Wrapper and facade for {@link IEscapyComponentFactory},
 * it collect many factories in one place, process their annotations
 * and build result components
 */
public interface IEscapyComponentMetaFactory extends IEscapyComponentFactory {

	void setComponentFactories(IEscapyComponentFactory... factories);

	IEscapyComponentFactory[] getComponentFactories();

}
package net.irregular.escapy.map.comp;

/**
 * Might be annotated by {@link EscapyComponent},
 * then it will be component namespace
 */public interface IEscapyComponentFactory {

	<T> T createComponent(String name);

}
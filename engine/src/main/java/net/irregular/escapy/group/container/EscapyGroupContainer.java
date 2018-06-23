package net.irregular.escapy.group.container;


/**
 * @author Henry on 28/07/17.
 */
public interface EscapyGroupContainer {

	boolean initialize();

	EscapyLocationContainer getLocationContainer();
	EscapyRendererContainer getRendererContainer();

}

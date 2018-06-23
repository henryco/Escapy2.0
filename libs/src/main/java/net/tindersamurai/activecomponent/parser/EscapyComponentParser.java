package net.tindersamurai.activecomponent.parser;


public interface EscapyComponentParser {
	<T> T parseComponent(String file) throws Exception;
}
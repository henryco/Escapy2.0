package net.irregular.escapy.map.data.parser;


public interface EscapyComponentParser {
	<T> T parseComponent(String file) throws Exception;
}
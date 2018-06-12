package net.irregular.escapy.map.data.parser;

import java.io.IOException;

public interface EscapyComponentParser {
	<T> T parseComponent(String file) throws Exception;
}
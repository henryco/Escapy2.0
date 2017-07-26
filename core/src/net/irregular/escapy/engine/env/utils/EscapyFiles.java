package net.irregular.escapy.engine.env.utils;

import static java.io.File.separator;

/**
 * @author Henry on 26/07/17.
 */ // TODO: 26/07/17
public interface EscapyFiles {

	static String safePath(String path) {
		return path.replace("\\", separator).replace("/", separator);
	}
}

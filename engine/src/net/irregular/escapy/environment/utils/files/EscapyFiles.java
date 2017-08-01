package net.irregular.escapy.environment.utils.files;

import static java.io.File.separator;

/**
 * @author Henry on 26/07/17.
 */ // TODO: 26/07/17
public interface EscapyFiles {

	static String safetyPath(String path) {
		return path.replace("\\", separator).replace("/", separator);
	}
}

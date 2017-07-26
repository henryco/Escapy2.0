package net.irregular.escapy.engine.env.utils.loader;

import net.irregular.escapy.engine.env.utils.EscapyFiles;

import java.util.List;


/**
 * @author Henry on 14/07/17.
 */
public interface EscapyLoaderUtils {

	default float[] floatListToArray(List<Float> floats) {
		return new float[]{floats.get(0), floats.get(1)};
	}

	default boolean[] booleanListToArray(List<Boolean> booleans) {
		return new boolean[]{booleans.get(0), booleans.get(1)};
	}

	default String safePath(String path) {
		return EscapyFiles.safePath(path);
	}
}
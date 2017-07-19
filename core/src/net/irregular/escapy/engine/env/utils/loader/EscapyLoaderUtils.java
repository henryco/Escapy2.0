package net.irregular.escapy.engine.env.utils.loader;

import java.util.List;

/**
 * @author Henry on 14/07/17.
 */
public interface EscapyLoaderUtils {

	default float[] floatListToArray(List<Float> floats) {
		return new float[]{floats.get(0), floats.get(1)};
	}

}

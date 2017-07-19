package net.irregular.escapy.engine.group.map.loader;

import java.util.List;

/**
 * @author Henry on 14/07/17.
 */
public interface LoaderUtils {

	default float[] floatListToArray(List<Float> floats) {
		return new float[]{floats.get(0), floats.get(1)};
	}

}

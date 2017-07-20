package net.irregular.escapy.engine.env.utils.serial;

import net.irregular.escapy.engine.env.utils.Named;

/**
 * @author Henry on 14/07/17.
 */
public interface EscapySerialized extends Named {

	default String getValidType() {
		return "";
	}
	default String getType() {
		return "";
	}
	default String getName() {
		return "";
	}

	default boolean isValid() {
		return getValidType().equals(getType());
	}

}

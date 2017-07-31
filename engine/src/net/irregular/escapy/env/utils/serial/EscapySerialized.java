package net.irregular.escapy.env.utils.serial;

import net.irregular.escapy.env.utils.Named;

import java.util.Collection;
import java.util.LinkedList;

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
	default Collection<String> getAttributes() {
		return new LinkedList<>();
	}

	default boolean isValid() {
		return getValidType().equals(getType());
	}

}

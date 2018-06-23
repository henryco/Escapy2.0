package net.irregular.escapy.utils.serial;

import net.irregular.escapy.utils.EscapyNamed;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Henry on 14/07/17.
 */
public interface EscapySerialized extends EscapyNamed {

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

package net.irregular.escapy.context.game.configuration.util;

/**
 * @author Henry on 26/07/17.
 */
public interface PropertyKeysStorage {

	PropertyKeysStorage addProperty(String key, Object property);

	PropertyKeysStorage addPropertyKey(String key);
	PropertyKeysStorage addPropertyValue(Object value);
	PropertyKeysStorage and();

	default void save() {
		this.and();
	}

}

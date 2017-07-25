package net.irregular.escapy.engine.env.context.game.configuration.util;

/**
 * @author Henry on 26/07/17.
 */
public interface PropertyKeysContainer {

	PropertyKeysContainer addProperty(String key, Object property);

	PropertyKeysContainer addPropertyKey(String key);
	PropertyKeysContainer addPropertyValue(Object value);
	PropertyKeysContainer and();

	default void save() {
		this.and();
	}

}

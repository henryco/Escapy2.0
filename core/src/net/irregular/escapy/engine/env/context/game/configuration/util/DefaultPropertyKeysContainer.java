package net.irregular.escapy.engine.env.context.game.configuration.util;

import java.util.Map;

/**
 * @author Henry on 26/07/17.
 */
public class DefaultPropertyKeysContainer implements PropertyKeysContainer{

	private final Map<String, Object> objectMap;
	public DefaultPropertyKeysContainer(Map<String, Object> objectMap) {
		this.objectMap = objectMap;
	}

	private String tempKey = null;
	private Object tempObj = null;

	@Override
	public PropertyKeysContainer addProperty(String key, Object property) {
		return and();
	}

	@Override
	public PropertyKeysContainer addPropertyKey(String key) {
		this.tempKey = key;
		return this;
	}

	@Override
	public PropertyKeysContainer addPropertyValue(Object value) {
		this.tempObj = value;
		return this;
	}

	@Override
	public PropertyKeysContainer and() {
		if (tempKey != null && tempObj != null)
			objectMap.put(tempKey, tempObj);
		tempKey = null;
		tempObj = null;
		return this;
	}
}

package net.irregular.escapy.context.game.configuration.util;

import java.util.Map;

/**
 * @author Henry on 26/07/17.
 */
public class DefaultPropertyKeysContainer implements PropertyKeysStorage {

	private final Map<String, Object> objectMap;
	public DefaultPropertyKeysContainer(Map<String, Object> objectMap) {
		this.objectMap = objectMap;
	}

	private String tempKey = null;
	private Object tempObj = null;

	@Override
	public PropertyKeysStorage addProperty(String key, Object property) {
		return and();
	}

	@Override
	public PropertyKeysStorage addPropertyKey(String key) {
		this.tempKey = key;
		return this;
	}

	@Override
	public PropertyKeysStorage addPropertyValue(Object value) {
		this.tempObj = value;
		return this;
	}

	@Override
	public PropertyKeysStorage and() {
		if (tempKey != null && tempObj != null)
			objectMap.put(tempKey, tempObj);
		tempKey = null;
		tempObj = null;
		return this;
	}
}

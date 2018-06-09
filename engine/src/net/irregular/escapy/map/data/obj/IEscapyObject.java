package net.irregular.escapy.map.data.obj;


import lombok.val;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Map.Entry;

public interface IEscapyObject {

	static IEscapyObject Default(Object object) {
		return new IEscapyObject() {
			@Override
			public <T> T getObject() {
				//noinspection unchecked
				return (T) object;
			}
		};
	}

	<T> T getObject();

	default Class<?> getObjectClass() {
		return getObject().getClass();
	}

	default String getObjectName() {
		return getObjectClass().getName();
	}

	default <T> T invokeMethod(String name, Entry<Class<?>, Object>[] args) {

		val argTypes = new Class[args.length];
		val argValues = new Object[args.length];
		for (int i = 0; i < args.length; i++) {
			argTypes[i] = args[i].getKey();
			argValues[i] = args[i].getValue();
		}

		final Method method; try {
			method = getObjectClass().getDeclaredMethod(name, argTypes);
			method.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
			// TODO LOGGING
			// right we just output err to console and log
			// because it should not stop the game process
		}

		try {
			// noinspection unchecked
			return (T) method.invoke(getObject(), argValues);
		} catch (IllegalAccessException | InvocationTargetException | ClassCastException e) {
			e.printStackTrace();
			// TODO LOGGING
			return null;
		}
	}

	default <T> T invokeMethod(String name, Object... args) {
		//noinspection unchecked
		Entry<Class<?>, Object>[] entries = new Entry[args.length];
		for (int i = 0; i < args.length; i++)
			entries[i] = new AbstractMap.SimpleEntry<>(args[i].getClass(), args[i]);

		//noinspection unchecked
		return (T) invokeMethod(name, entries);
	}

}
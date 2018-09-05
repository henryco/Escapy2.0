package net.tindersamurai.activecomponent.obj;

import lombok.val;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map.Entry;

public interface IEscapyObjectFactory {

	IEscapyObject createObject(String name, Object... args);
	IEscapyObject createObject(String name, Entry<Class<?>, Object>[] args);

	static IEscapyObjectFactory Default() {

		return new IEscapyObjectFactory() {

			@Override
			public IEscapyObject createObject(String name, Entry<Class<?>, Object>[] args) {

				final Class<?> cla; try {
					cla = Class.forName(name);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					// TODO LOGGING
					throw new RuntimeException("Cannot find object class: " + name);
				}

				val argTypes = new Class[args.length];
				val argValues = new Object[args.length];
				for (int i = 0; i < args.length; i++) {
					argTypes[i] = args[i].getKey();
					argValues[i] = args[i].getValue();
				}

				final Constructor<?> constructor; try {
					constructor = cla.getDeclaredConstructor(argTypes);
					constructor.setAccessible(true);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
					// TODO LOGGING
					throw new RuntimeException("Cannot find object constructor: " + name + " " + Arrays.toString(argTypes));
				}

				try {
					return IEscapyObject.Default(constructor.newInstance(argValues));
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
					throw new RuntimeException("Cannot create object: " + name);
				}
			}

			@Override
			public IEscapyObject createObject(String name, Object... args) {
				//noinspection unchecked
				Entry<Class<?>, Object>[] entries = new Entry[args.length];
				for (int i = 0; i < args.length; i++)
					entries[i] = new AbstractMap.SimpleEntry<>(args[i].getClass(), args[i]);
				return createObject(name, entries);
			}
		};
	}

}
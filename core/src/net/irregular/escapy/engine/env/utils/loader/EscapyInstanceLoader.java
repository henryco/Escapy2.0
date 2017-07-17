package net.irregular.escapy.engine.env.utils.loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author Henry on 14/07/17.
 */
public interface EscapyInstanceLoader<T> {


	@SuppressWarnings("unchecked")
	default T loadInstance(String name, Object ... args) {
		try {

			Method[] methods = this.getClass().getDeclaredMethods();

			for (Method method: methods) {
				EscapyInstanced named = method.getAnnotation(EscapyInstanced.class);
				if (named != null && named.value().equals(name))
					return (T) method.invoke(this, args);
			}

			for (Method method: methods) {
				if (method.getAnnotation(EscapyInstanced.class) != null
						&& method.getName().equals(name))
					return (T) method.invoke(this, args);
			}

		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}


	default T loadInstanceAttributes(T instance, String ... attributes) {
		if (instance == null) return instance;
		for (String attr: attributes) {
			T loaded = this.loadInstance(attr, instance);
			instance = loaded != null ? loaded : instance;
		}
		return instance;
	}

	default T loadInstanceAttributes(T instance, Collection<String> attributes) {
		return loadInstanceAttributes(instance, attributes.toArray(new String[0]));
	}

}

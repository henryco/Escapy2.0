package net.irregular.escapy.environment.utils.loader;

import net.irregular.escapy.environment.utils.EscapyLogger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author Henry on 14/07/17.
 */
public interface EscapyInstanceLoader<INSTANCE_TYPE> {


	@SuppressWarnings("unchecked")
	default INSTANCE_TYPE loadInstance(String name, Object ... args) {
		try {

			Method[] methods = this.getClass().getDeclaredMethods();

			for (Method method: methods) {
				EscapyInstanced named = method.getAnnotation(EscapyInstanced.class);
				if (named != null && named.value().equals(name)) {
					return (INSTANCE_TYPE) method.invoke(this, args);
				}
			}

			for (Method method: methods) {
				if (method.getAnnotation(EscapyInstanced.class) != null
						&& method.getName().equals(name))
					return (INSTANCE_TYPE) method.invoke(this, args);
			}

		} catch (IllegalAccessException | InvocationTargetException e) {
			if (e instanceof InvocationTargetException) {
				Throwable targetException = ((InvocationTargetException) e).getTargetException();
				new EscapyLogger("LoadInstance").fileJava().log(targetException, true);
			} else new EscapyLogger("LoadInstance").fileJava().log(e, true);
		}
		return null;
	}


	default INSTANCE_TYPE loadInstanceAttributes(INSTANCE_TYPE instance, String ... attributes) {
		if (instance == null) return instance;
		for (String attr: attributes) {
			INSTANCE_TYPE loaded = this.loadInstance(attr, instance);
			instance = loaded != null ? loaded : instance;
		}
		return instance;
	}

	default INSTANCE_TYPE loadInstanceAttributes(INSTANCE_TYPE instance, Collection<String> attributes) {
		if (attributes == null || attributes.isEmpty()) return instance;
		return loadInstanceAttributes(instance, attributes.toArray(new String[0]));
	}


}

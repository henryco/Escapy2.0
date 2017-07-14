package net.irregular.escapy.engine.env.utils.loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Henry on 14/07/17.
 */
public interface EscapyInstanceLoader {


	@SuppressWarnings("unchecked")
	default <T> T load(String name, Object ... args) {
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

}

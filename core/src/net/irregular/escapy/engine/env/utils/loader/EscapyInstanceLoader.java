package net.irregular.escapy.engine.env.utils.loader;

import javax.inject.Named;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Henry on 14/07/17.
 */
public interface EscapyInstanceLoader {


	@SuppressWarnings("unchecked")
	default <T> T load(String name) {
		try {

			Method[] methods = this.getClass().getDeclaredMethods();

			for (Method method: methods) {
				Named named = method.getAnnotation(Named.class);
				if (named != null && named.value().equals(name))
					return (T) method.invoke(this);
			}

			for (Method method: methods) {
				if (method.getName().equals(name))
					return (T) method.invoke(this);
			}

		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}

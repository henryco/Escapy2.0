package net.irregular.escapy.desktop.loader;


import com.google.gson.Gson;
import net.irregular.escapy.engine.env.utils.EscapySerialized;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;

/**
 * @author Henry on 15/07/17.
 */
public class DefaultDesktopConfigLoaderBuilder {

	private String path;
	private String name;

	private Class loadedClass;
	private Class<? extends EscapySerialized> serializedClass;


	public DefaultDesktopConfigLoaderBuilder() {}

	public DefaultDesktopConfigLoaderBuilder(String path) {
		this();
		setPath(path);
	}

	public DefaultDesktopConfigLoaderBuilder(String path, String name) {
		this(path);
		setName(name);
	}

	public DefaultDesktopConfigLoaderBuilder(Class loadedClass,
											 Class<? extends EscapySerialized> serializedClass) {
		this();
		this.loadedClass = loadedClass;
		this.serializedClass = serializedClass;
	}

	public DefaultDesktopConfigLoaderBuilder(String path,
											 String name,
											 Class loadedClass,
											 Class<? extends EscapySerialized> serializedClass) {
		this(name, path);
		this.loadedClass = loadedClass;
		this.serializedClass = serializedClass;
	}



	@SuppressWarnings("unchecked")
	public DesktopConfigLoader build() {

		return new DesktopConfigLoader() {
			@Override
			public <T> T loadDesktopConfig() {

				try {
					Class<T> loadableClass = loadedClass;

					T loadableInstance = loadableClass.newInstance();
					Reader reader = new InputStreamReader(new FileInputStream(path));
					EscapySerialized serialized = new Gson().fromJson(reader, serializedClass);

					if (serialized == null || !serialized.isValid()) return null;
					if (name != null && !name.isEmpty() && !name.equals(serialized.getName())) return null;

					for (Field field: serializedClass.getDeclaredFields()) {
						try {
							Field loadableField = loadableClass.getDeclaredField(field.getName());
							loadableField.setAccessible(true);
							field.setAccessible(true);
							loadableField.set(loadableInstance, field.get(serialized));
						} catch (Exception ignored){}
					}

					return loadableInstance;

				} catch (Exception e) {e.printStackTrace();}
				return null;
			}
		};
	}




	public DefaultDesktopConfigLoaderBuilder setPath(String path) {
		this.path = path;
		return this;
	}

	public DefaultDesktopConfigLoaderBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public DefaultDesktopConfigLoaderBuilder setLoadedClass(Class loadedClass) {
		this.loadedClass = loadedClass;
		return this;
	}

	public DefaultDesktopConfigLoaderBuilder setSerializedClass(Class<? extends EscapySerialized> serializedClass) {
		this.serializedClass = serializedClass;
		return this;
	}
}

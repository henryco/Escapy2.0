package net.irregular.escapy.map.data.core;

import net.irregular.escapy.map.data.comp.annotation.Arg;
import net.irregular.escapy.map.data.comp.annotation.EscapyComponent;
import net.irregular.escapy.map.data.comp.annotation.EscapyComponentFactory;

import java.lang.reflect.Array;

@EscapyComponent("u") // u for utility
public class UtilityCoreComponent {

	@EscapyComponentFactory("p") // p for primitive
	public static final class PrimitivesUtilities {

		@EscapyComponent("type")
		public Class<?> primitiveType(@Arg("object") Object o) {
			try {
				return (Class<?>) o.getClass().getField("TYPE").get(null);
			} catch (IllegalAccessException | NoSuchFieldException e) {
				e.printStackTrace();
				throw new RuntimeException("Object: " + o + " is not primitive!");
			}
		}

		@EscapyComponent("value")
		public <T> T primitiveValue(@Arg("object") Object o) {
			return null; // todo
		}

	}


	@EscapyComponent("array")
	public <T> T[] newArrayInstance(@Arg("type") Class<T> type, T ... args) {
		//noinspection unchecked
		T[] array = (T[]) Array.newInstance(type, args.length);
		System.arraycopy(args, 0, array, 0, args.length);
		return array;
	}

	@EscapyComponent("class")
	public Class<?> findClass(@Arg("object") Object o) {
		return o.getClass();
	}

}
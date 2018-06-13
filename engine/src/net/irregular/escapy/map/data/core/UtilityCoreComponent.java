package net.irregular.escapy.map.data.core;

import com.sun.corba.se.impl.io.TypeMismatchException;
import net.irregular.escapy.map.data.comp.annotation.Arg;
import net.irregular.escapy.map.data.comp.annotation.EscapyComponent;
import net.irregular.escapy.map.data.comp.annotation.EscapyComponentFactory;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

@EscapyComponentFactory("u") // u for utility
public class UtilityCoreComponent {

	@EscapyComponentFactory("p") // p for primitive
	public static final class PrimitivesUtilities {

		@EscapyComponent("type") // test: OK
		public Class<?> primitiveType(@Arg("object") Object o) {
			try {
				return (Class<?>) o.getClass().getField("TYPE").get(null);
			} catch (IllegalAccessException | NoSuchFieldException e) {
				e.printStackTrace();
				throw new RuntimeException("Object: " + o + " is not primitive!");
			}
		}

		@EscapyComponent("value") // test: OK
		public Object primitiveValue(@Arg("object") Object o) {

			if(o == null)
				return null;
			if (o instanceof Integer)
				return ((Integer) o).intValue();
			if (o instanceof Float)
				return ((Float) o).floatValue();
			if (o instanceof Double)
				return ((Double) o).doubleValue();
			if (o instanceof Long)
				return ((Long) o).longValue();
			if (o instanceof Short)
				return ((Short) o).shortValue();
			if (o instanceof Byte)
				return ((Byte) o).byteValue();
			if (o instanceof Character)
				return ((Character) o).charValue();

			throw new TypeMismatchException();
		}

	}


	@EscapyComponent("array") // test: OK
	public Object newArrayInstance(@Arg("type") Class<?> type, Object ... args) {
		final int l = Array.getLength(args);
		Object array = Array.newInstance(type, l);
		for (int i = 0; i < l; i++)
			Array.set(array, i, Array.get(args, i));
		return array;
	}

	@EscapyComponent("class") // test: OK
	public Class<?> findClass(@Arg("object") Object o) {
		return o.getClass();
	}

}
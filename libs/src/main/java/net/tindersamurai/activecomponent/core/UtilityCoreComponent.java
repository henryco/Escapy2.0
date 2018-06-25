package net.tindersamurai.activecomponent.core;

import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;

import java.lang.reflect.Array;

@EscapyComponentFactory("u") // u for utility
public class UtilityCoreComponent {

	@EscapyComponentFactory("p") // p for primitive
	public static final class PrimitivesUtilities {

		@EscapyComponent("type") // test: OK
		public Class<?> primitiveType(@Arg("object") Object o) {
			try {
				return (Class<?>) o.getClass().getField("TYPE").get(null);
			} catch (IllegalAccessException | NoSuchFieldException e) {
				throw new RuntimeException("Object: " + o + " is not primitive!", e);
			}
		}

	}

	@EscapyComponent("debug") // test: OK
	public void debug(Object ... args) {

		System.out.println("\n<c:u.debug>");
		for (Object arg : args) try {
			if (arg.getClass().isArray()) {

				final int l = Array.getLength(arg);
				StringBuilder ars = new StringBuilder();
				for (int i = 0; i < l; i++)
					ars.append(Array.get(arg, i)).append(", ");
				String res = ars.substring(0, ars.length() - 2);
				System.out.println("\t" + arg.getClass() + ": [" + res + "]");

			} else {
				System.out.println("\t" + arg.getClass() + ": " + arg);
			}
		} catch (NullPointerException e) {
			System.out.println("null");
		}
		System.out.println("</c:u.debug>\n");
	}

	@EscapyComponent("main") // test OK
	public void main(Object ... args) {
		/* VOID */
	}

	@EscapyComponent("array") // test: OK
	public Object newArrayInstance(@Arg("type") Class<?> type, @Arg("elements") Object ... args) {
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
package net.tindersamurai.activecomponent.core;

import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.activecomponent.parser.EscapyComponentParserProvider;

import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

@EscapyComponentFactory("u") // u for utility
public final class UtilityCoreComponent {

	@EscapyComponentFactory("p") // p for Primitive
	public static final class PrimitivesUtilities {

		@EscapyComponent("type") // test: OK
		public Class<?> primitiveType(@Arg("object") Object o) {
			System.out.println(o);
			if (o instanceof String) {
				String oo = (String) o;
				String cl = "java.lang." + oo.substring(0, 1).toUpperCase() + oo.substring(1);
				try {
					return (Class<?>) Class.forName(cl).getDeclaredField("TYPE").get(null);
				} catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
					throw new RuntimeException("Object: " + o + " is not primitive!", e);
				}
			}
			try {
				return (Class<?>) o.getClass().getField("TYPE").get(null);
			} catch (IllegalAccessException | NoSuchFieldException e) {
				throw new RuntimeException("Object: " + o + " is not primitive!", e);
			}
		}

	}

	@EscapyComponentFactory("s") // s for System
	public static final class SystemUtilities {

		@EscapyComponent("property")
		public String property(@Arg("key") String key) {
			return System.getProperty(key);
		}

		@EscapyComponent("exit")
		public void exit(@Arg("status") int status) {
			System.exit(status);
		}
	}

	@EscapyComponentFactory("l") // l for Loader
	public static final class LoaderUtilities implements EscapyComponentParserProvider {

		private EscapyComponentParser parser;

		@Override
		public void provideParser(EscapyComponentParser parser) {
			this.parser = parser;
		}

		@EscapyComponent("external")
		public <T> T external(String file) {
			return parser.parseComponent(file);
		}
	}

	@EscapyComponent("debug") // test: OK
	public void debug(@Arg("args") Object ... args) {

		System.out.println("\n<c:u.debug>");
		for (Object arg : args) try {
			if (arg.getClass().isArray()) {

				final int l = Array.getLength(arg);
				StringBuilder ars = new StringBuilder();
				for (int i = 0; i < l; i++)
					ars.append(Array.get(arg, i)).append(", ");
				String res = ars.substring(0, Math.max(0, ars.length() - 2));
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
	public Object newArrayInstance(@Arg("type") Class<?> type,
								   @Arg("elements") Object ... args) {
		final int l = Array.getLength(args);
		Object array = Array.newInstance(type, l);
		for (int i = 0; i < l; i++)
			Array.set(array, i, Array.get(args, i));
		return array;
	}

	@EscapyComponent("array-auto")
	public Object newAutoArray(@Arg("elements") Object ... args) {
		return newArrayInstance(args[0].getClass(), args);
	}

	@EscapyComponent("array-list")
	public List<?> newArrayList(@Arg("elements") Object ... args) {
		return new ArrayList<>(Arrays.asList(args));
	}

	@EscapyComponent("class") // test: OK
	public Class<?> findClass(@Arg("object") Object o) {
		return o.getClass();
	}

	@EscapyComponent("class-by-name")
	public Class<?> classByName(String name) {
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Unknown class", e);
		}
	}

	@EscapyComponent("entry")
	public Entry createEntry(@Arg("key") Object key,
							 @Arg("value") Object value
	) {
		return new AbstractMap.SimpleImmutableEntry<>(key, value);
	}

	@EscapyComponent("null")
	public Object nullObject(Object ... args) {
		return null;
	}
}
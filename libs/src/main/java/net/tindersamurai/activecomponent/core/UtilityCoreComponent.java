package net.tindersamurai.activecomponent.core;

import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.activecomponent.comp.annotation.Arg;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponent;
import net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory;
import net.tindersamurai.activecomponent.comp.annotation.NotNull;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.activecomponent.parser.EscapyComponentParserProvider;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Log @EscapyComponentFactory("u") // u for utility
public final class UtilityCoreComponent {

	@EscapyComponentFactory("p") // p for Primitive
	public static final class PrimitivesUtilities {

		@EscapyComponent("type") // test: OK
		public Class<?> primitiveType(@NotNull @Arg("object") Object o) {
			log.info(""+o);
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
		public String property(@NotNull @Arg("key") String key) {
			return System.getProperty(key);
		}

		@EscapyComponent("exit")
		public void exit(@NotNull @Arg("status") int status) {
			System.exit(status);
		}
	}

	@EscapyComponent("debug") // test: OK
	public void debug(@Arg("args") Object ... args) {

		log.info("\n<c:u.debug>");
		for (Object arg : args) try {
			if (arg.getClass().isArray()) {

				final int l = Array.getLength(arg);
				StringBuilder ars = new StringBuilder();
				for (int i = 0; i < l; i++)
					ars.append(Array.get(arg, i)).append(", ");
				String res = ars.substring(0, Math.max(0, ars.length() - 2));
				log.info("\t" + arg.getClass() + ": [" + res + "]");

			} else {
				log.info("\t" + arg.getClass() + ": " + arg);
			}
		} catch (NullPointerException e) {
			log.info("null");
		}
		log.info("</c:u.debug>\n");
	}

	@EscapyComponent("main") // test OK
	public void main(Object ... args) {
		/* VOID */
	}

	@EscapyComponent("array") // test: OK
	public Object newArrayInstance(@NotNull @Arg("type") Class<?> type,
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
	public <T> List<T> newArrayList(@Arg("elements") T ... args) {
		return new ArrayList<>(Arrays.asList(args));
	}

	@EscapyComponent("class") // test: OK
	public Class<?> findClass(@NotNull @Arg("object") Object o) {
		return o.getClass();
	}

	@EscapyComponent("class-by-name")
	public Class<?> classByName(@NotNull @Arg("name") String name) {
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Unknown class", e);
		}
	}

	@EscapyComponent("entry")
	public <K, V> Entry<K, V> createEntry(
			@Arg("key") K key, @Arg("value") V value
	) {
		return new AbstractMap.SimpleImmutableEntry<>(key, value);
	}

	@EscapyComponent("map")
	public <K, V> Map<K, V> createMap(@Arg("entries") Entry<K, V> ... entries) {
		return Arrays.stream(entries).collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b));
	}

	@EscapyComponent("null")
	public Object nullObject(Object ... args) {
		return null;
	}
}
package net.irregular.escapy.map.data.comp.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({TYPE})
public @interface EscapyComponentFactory {
	String value() default "";
	String name() default "";

	final class Helper {
		public static String getName(EscapyComponentFactory annotation, Class<?> source) {
			return !annotation.value().isEmpty()
					? annotation.value()
					: !annotation.name().isEmpty()
						? annotation.name()
						: source.getSimpleName()
			;
		}
	}
}
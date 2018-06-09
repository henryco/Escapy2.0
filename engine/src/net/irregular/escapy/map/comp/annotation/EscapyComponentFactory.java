package net.irregular.escapy.map.comp.annotation;

import net.irregular.escapy.map.comp.factory.EscapyComponentFactoryMetaData;

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
		public static EscapyComponentFactoryMetaData getMetaData(EscapyComponentFactory annotation, Class<?> source) {
			return new EscapyComponentFactoryMetaData(
					!annotation.value().isEmpty()
							? annotation.value()
							: !annotation.name().isEmpty()
							? annotation.name()
							: source.getSimpleName()
			);
		}
	}
}
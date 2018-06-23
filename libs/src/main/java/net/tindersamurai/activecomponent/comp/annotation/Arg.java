package net.tindersamurai.activecomponent.comp.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({PARAMETER})
public @interface Arg {
	/**
	 * Argument name required
	 * for serialization binding
	 */ String value();
}
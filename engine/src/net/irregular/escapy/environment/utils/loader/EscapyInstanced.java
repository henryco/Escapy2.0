package net.irregular.escapy.environment.utils.loader;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Henry on 14/07/17.
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD})
public @interface EscapyInstanced {
	String value() default "";
}

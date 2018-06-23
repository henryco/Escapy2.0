package net.irregular.escapy.context.annotation.meta.name;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Henry on 28/06/17.
 */

@Documented
@Retention(RUNTIME)
@Target(ANNOTATION_TYPE)
public @interface ScreenMapProcessor {
	Class<? extends ScreenMapProcessorExecutor> value();
}

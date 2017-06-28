package net.irregular.escapy.engine.env.context.annotation;

import net.irregular.escapy.engine.env.context.annotation.meta.name.ScreenMapProcessor;
import net.irregular.escapy.engine.env.context.annotation.meta.name.ScreenMapProcessorExecutorImp;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Henry on 28/06/17.
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@ScreenMapProcessor(ScreenMapProcessorExecutorImp.class)
public @interface ScreenName {
	String value();
}

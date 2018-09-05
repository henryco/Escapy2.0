package net.tindersamurai.escapy.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * This annotation is just a marker and will be discarded by compiler.
 * @author Henry on 29/06/17.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({METHOD, TYPE, FIELD, CONSTRUCTOR})
public @interface EscapyAPI {
}

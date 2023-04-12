package com.company.core.enums;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * This annotation should be used on the APIs and Controllers which are not
 * deprecated.
 *
 * @author mukulbansal
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {METHOD})
public @interface DeprecatedApi {
    String alternate() default "NONE";
}
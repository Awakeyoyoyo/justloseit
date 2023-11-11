package com.awake.anno;

import java.lang.annotation.*;

/**
 * @author awakeyoyoyo
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EntityCache {

    Cache cache() default @Cache;

    Persister persister() default @Persister;
}

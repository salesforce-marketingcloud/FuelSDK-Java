package com.exacttarget.fuelsdk.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InternalRestType {
	String type();
	String collectionKey();
	String[] ignoredFields() default {};
	String restPath();
}

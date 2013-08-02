package com.exacttarget.fuelsdk.annotations;

import com.exacttarget.fuelsdk.internal.APIObject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InternalType {
    Class<? extends APIObject> type();
}

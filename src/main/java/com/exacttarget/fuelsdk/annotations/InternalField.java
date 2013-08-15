package com.exacttarget.fuelsdk.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InternalField {
    /**
     * The name of the Java field on the corresponding internal serializable class.
     * @return
     */
    String name();
    /**
     * The Actual Serialized name for SOAP.  For Special Cases
     * @return
     */
    String serializedName() default "";
}

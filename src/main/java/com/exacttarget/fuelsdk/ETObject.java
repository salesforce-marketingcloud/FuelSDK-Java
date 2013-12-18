//
// ETObject.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk;

import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class ETObject {
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
